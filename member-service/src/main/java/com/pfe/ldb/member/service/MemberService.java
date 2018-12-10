package com.pfe.ldb.member.service;

import java.util.List;

import com.pfe.ldb.member.dao.exception.MemberEntityNotFoundException;
import com.pfe.ldb.member.dao.exception.UserEntityNotFoundException;
import com.pfe.ldb.member.dto.MemberCreateDTO;
import com.pfe.ldb.member.dto.MemberDTO;
import com.pfe.ldb.member.dto.MemberUpdateDTO;

public interface MemberService {

	MemberDTO getMemberById(final Integer id) throws MemberEntityNotFoundException;


	MemberDTO getMemberByUserId(final Integer userId) throws UserEntityNotFoundException;


	List<MemberDTO> getAllMembers();


	MemberDTO createMember(final MemberCreateDTO memberCreateDTO)
		throws UserEntityNotFoundException;


	MemberDTO updateMember(final Integer id, final MemberUpdateDTO memberUpdateDTO)
		throws MemberEntityNotFoundException,
		UserEntityNotFoundException;


	void deleteMemberById(final Integer id) throws MemberEntityNotFoundException;
}
