package com.pfe.ldb.member.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.pfe.ldb.entities.MemberEntity;
import com.pfe.ldb.entities.UserEntity;
import com.pfe.ldb.member.models.MemberDTO;
import com.pfe.ldb.member.repositories.MemberRepository;
import com.pfe.ldb.member.repositories.UserRepository;
import com.pfe.ldb.member.repositories.exceptions.MemberEntityNotFoundException;
import com.pfe.ldb.member.repositories.exceptions.UserEntityNotFoundException;

public class DefaultMemberService implements MemberService {

	private @Autowired MemberRepository memberRepository;
	
	private @Autowired UserRepository userRepository;

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

		if (!userRepository.existsById(id)) {
			throw new UserEntityNotFoundException();
		}

		final UserEntity userEntity = userRepository.findById(id).get();
		final MemberEntity memberEntity = userEntity.getMember();

		return modelMapper.map(memberEntity, MemberDTO.class);
	}

	@Override
	public MemberDTO createMember(final MemberDTO memberDTO) {

		MemberEntity memberEntityToSave = modelMapper.map(memberDTO, MemberEntity.class);
		final MemberEntity memberEntity = memberRepository.save(memberEntityToSave);

		return modelMapper.map(memberEntity, MemberDTO.class);
	}

	@Override
	public MemberDTO updateMember(final Integer id, final MemberDTO memberDTO) throws MemberEntityNotFoundException {

		if (!memberRepository.existsById(id)) {
			throw new MemberEntityNotFoundException();
		}

		final MemberEntity memberEntityToUpdate = memberRepository.findById(id).get();
		modelMapper.map(memberDTO, memberEntityToUpdate);
		final MemberEntity memberEntity = memberRepository.save(memberEntityToUpdate);

		return modelMapper.map(memberEntity, MemberDTO.class);
	}

	@Override
	public void deleteMemberById(final Integer id) throws MemberEntityNotFoundException {

		if (!memberRepository.existsById(id)) {
			throw new MemberEntityNotFoundException();
		}

		memberRepository.deleteById(id);
	}
}
