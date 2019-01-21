package com.pfe.ldb.member.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import model.MemberEntity;
import model.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfe.ldb.member.dao.exception.MemberEntityNotFoundException;
import com.pfe.ldb.member.dao.exception.UserEntityNotFoundException;
import com.pfe.ldb.member.dao.repository.MemberRepository;
import com.pfe.ldb.member.dao.repository.UserRepository;
import com.pfe.ldb.member.dto.MemberCreateDTO;
import com.pfe.ldb.member.dto.MemberDTO;
import com.pfe.ldb.member.dto.MemberUpdateDTO;

@Transactional
@Service
public class DefaultMemberService implements MemberService {

	private @Autowired MemberRepository memberRepository;
	private @Autowired UserRepository userRepository;

	private @Autowired ModelMapper modelMapper;


	@Override
	public MemberDTO getMemberById(final Integer id) throws MemberEntityNotFoundException {

		return modelMapper.map(findMemberEntityById(id), MemberDTO.class);
	}


	@Override
	public MemberDTO getMemberByUserId(final Integer id) throws UserEntityNotFoundException {

		final UserEntity userEntity = findUserEntityById(id);

		return modelMapper.map(userEntity.getMember(), MemberDTO.class);
	}


	@Override
	public List<MemberDTO> getAllMembers() {

		final Iterable<MemberEntity> memberGroupEntities = memberRepository.findAll();

		return StreamSupport.stream(memberGroupEntities.spliterator(), true)
				.map(memberGroupEntity -> modelMapper.map(memberGroupEntity, MemberDTO.class))
				.collect(Collectors.toList());
	}


	@Override
	public MemberDTO createMember(final MemberCreateDTO dto) throws UserEntityNotFoundException {

		MemberEntity toCreate = modelMapper.map(dto, MemberEntity.class);
		toCreate.setUser(findUserEntityById(dto.getUserId()));
		final MemberEntity saved = memberRepository.save(toCreate);

		return modelMapper.map(saved, MemberDTO.class);
	}


	@Override
	public MemberDTO updateMember(final Integer id, final MemberUpdateDTO dto)
		throws MemberEntityNotFoundException,
		UserEntityNotFoundException {

		final MemberEntity toUpdate = findMemberEntityById(id);
		modelMapper.map(dto, toUpdate);

		if(!isSameUser(dto, toUpdate)) {
			toUpdate.setUser(findUserEntityById(dto.getUserId()));
		}

		final MemberEntity saved = memberRepository.save(toUpdate);

		return modelMapper.map(saved, MemberDTO.class);
	}


	@Override
	public void deleteMemberById(final Integer id) throws MemberEntityNotFoundException {

		memberRepository.delete(findMemberEntityById(id));
	}


	private MemberEntity findMemberEntityById(final Integer id)
		throws MemberEntityNotFoundException {

		final Optional<MemberEntity> memberEntity = memberRepository.findById(id);

		if(!memberEntity.isPresent()) {
			throw new MemberEntityNotFoundException();
		}

		return memberEntity.get();
	}


	private UserEntity findUserEntityById(final Integer id)
		throws UserEntityNotFoundException {

		final Optional<UserEntity> userEntity = userRepository.findById(id);

		if(!userEntity.isPresent()) {
			throw new UserEntityNotFoundException();
		}

		return userEntity.get();
	}


	private boolean isSameUser(final MemberUpdateDTO dto, final MemberEntity entity) {

		if(entity.getUser() == null) {
			return false;
		}

		return entity.getUser().getId().equals(dto.getUserId());
	}
}
