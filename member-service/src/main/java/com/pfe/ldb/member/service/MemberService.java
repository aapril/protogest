package com.pfe.ldb.member.service;

import java.util.List;

import com.pfe.ldb.member.dao.exception.MemberEntityNotFoundException;
import com.pfe.ldb.member.dao.exception.UserEntityNotFoundException;
import com.pfe.ldb.member.dto.MemberCreateDTO;
import com.pfe.ldb.member.dto.MemberDTO;
import com.pfe.ldb.member.dto.MemberUpdateDTO;

public interface MemberService {

	List<MemberDTO> getAllMembers();

	MemberDTO getMemberById(final Integer id) throws MemberEntityNotFoundException;

	MemberDTO createMember(final MemberCreateDTO memberCreateDTO);

	MemberDTO updateMember(final MemberUpdateDTO memberUpdateDTO) throws MemberEntityNotFoundException;

	void deleteMemberById(final Integer id) throws MemberEntityNotFoundException;

	MemberDTO getMemberByUserId(final Integer userId) throws UserEntityNotFoundException;
}
