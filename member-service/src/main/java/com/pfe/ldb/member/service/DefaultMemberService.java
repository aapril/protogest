package com.pfe.ldb.member.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfe.ldb.member.dao.entity.MemberEntity;
import com.pfe.ldb.member.dao.exception.MemberEntityNotFoundException;
import com.pfe.ldb.member.dao.exception.UserEntityNotFoundException;
import com.pfe.ldb.member.dao.repository.MemberRepository;
import com.pfe.ldb.member.dto.MemberCreateDTO;
import com.pfe.ldb.member.dto.MemberDTO;
import com.pfe.ldb.member.dto.MemberUpdateDTO;

@Transactional
@Service
public class DefaultMemberService implements MemberService {

	private @Autowired MemberRepository memberRepository;

	private @Autowired ModelMapper modelMapper;

	@Override
	public List<MemberDTO> getAllMembers() {

		final Iterable<MemberEntity> memberGroupEntities = memberRepository.findAll();

		return StreamSupport.stream(memberGroupEntities.spliterator(), true)
				.map(memberGroupEntity -> modelMapper.map(memberGroupEntity, MemberDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public MemberDTO getMemberById(final Integer id) throws MemberEntityNotFoundException {

		if (!memberRepository.existsById(id)) {
			throw new MemberEntityNotFoundException();
		}

		final MemberEntity memberEntity = memberRepository.findById(id).get();

		return modelMapper.map(memberEntity, MemberDTO.class);
	}

	@Override
	public MemberDTO getMemberByUserId(final Integer id) throws UserEntityNotFoundException {

		final Optional<MemberEntity> memberEntity = memberRepository.findByUserId(id);
		
		if (!memberEntity.isPresent()) {
			throw new UserEntityNotFoundException();
		}

		return modelMapper.map(memberEntity.get(), MemberDTO.class);
	}

	@Override
	public MemberDTO createMember(final MemberCreateDTO memberCreateDTO) {

		MemberEntity memberEntityToSave = modelMapper.map(memberCreateDTO, MemberEntity.class);
		final MemberEntity memberEntity = memberRepository.save(memberEntityToSave);

		return modelMapper.map(memberEntity, MemberDTO.class);
	}

	@Override
	public MemberDTO updateMember(final MemberUpdateDTO memberUpdateDTO) throws MemberEntityNotFoundException {

		final Optional<MemberEntity> memberEntity = memberRepository.findById(memberUpdateDTO.getId());

		if (!memberEntity.isPresent()) {
			throw new MemberEntityNotFoundException();
		}

		final MemberEntity toUpdate = memberEntity.get();
		modelMapper.map(memberUpdateDTO, toUpdate);
		final MemberEntity saved = memberRepository.save(toUpdate);

		return modelMapper.map(saved, MemberDTO.class);
	}

	@Override
	public void deleteMemberById(final Integer id) throws MemberEntityNotFoundException {

		if (!memberRepository.existsById(id)) {
			throw new MemberEntityNotFoundException();
		}

		memberRepository.deleteById(id);
	}
}
