package com.pfe.ldb.member.services;

import java.util.List;

import com.pfe.ldb.member.models.MemberCreateDTO;
import com.pfe.ldb.member.models.MemberDTO;
import com.pfe.ldb.member.models.MemberUpdateDTO;
import com.pfe.ldb.repositories.exceptions.MemberEntityNotFoundException;
import com.pfe.ldb.repositories.exceptions.UserEntityNotFoundException;

public interface MemberService {

	List<MemberDTO> getAllMembers();

	MemberDTO getMemberById(final Integer id) throws MemberEntityNotFoundException;

	MemberDTO createMember(final MemberCreateDTO memberCreateDTO);

	MemberDTO updateMember(final MemberUpdateDTO memberUpdateDTO) throws MemberEntityNotFoundException;

	void deleteMemberById(final Integer id) throws MemberEntityNotFoundException;

	MemberDTO getMemberByUserId(final Integer userId) throws UserEntityNotFoundException;
}
