package com.pfe.ldb.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.pfe.ldb.member.dao.exception.MemberEntityNotFoundException;
import com.pfe.ldb.member.dao.exception.UserEntityNotFoundException;
import com.pfe.ldb.member.dto.MemberCreateDTO;
import com.pfe.ldb.member.dto.MemberDTO;
import com.pfe.ldb.member.dto.MemberUpdateDTO;
import com.pfe.ldb.member.service.MemberService;

import io.swagger.annotations.ApiOperation;

@RestController
@EnableWebMvc
public class MemberController {

	private @Autowired MemberService memberService;

	@GetMapping("/member/all")
	@ApiOperation(value = "Get a list of all members.", response = MemberDTO.class, responseContainer = "List")
	public ResponseEntity<List<MemberDTO>> getAllMembers() {

		List<MemberDTO> responseBody = memberService.getAllMembers();

		return ResponseEntity.ok().body(responseBody);
	}

	
	@GetMapping("/member/{id}")
	@ApiOperation(value = "Get a member by id.", response = MemberDTO.class)
	public ResponseEntity<MemberDTO> getMemberById(final @PathVariable Integer id) {

		try {
			final MemberDTO responseBody = memberService.getMemberById(id);

			return ResponseEntity.ok().body(responseBody);

		} catch (final MemberEntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	
	@GetMapping("/member/")
	@ApiOperation(value = "Get a member by a user id.", response = MemberDTO.class)
	public ResponseEntity<MemberDTO> getMemberByUserId(final @RequestParam Integer userId) {

		try {
			final MemberDTO responseBody = memberService.getMemberByUserId(userId);

			return ResponseEntity.ok().body(responseBody);

		} catch (final UserEntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	
	@PostMapping("/member")
	@ApiOperation(value = "Add a member.", response = MemberCreateDTO.class)
	public ResponseEntity<MemberDTO> createMember(final @Validated @RequestBody MemberCreateDTO memberCreateDTO) {

		final MemberDTO responseBody = memberService.createMember(memberCreateDTO);

		return ResponseEntity.ok().body(responseBody);
	}

	
	@PutMapping("/member/{id}")
	@ApiOperation(value = "Update a member.", response = MemberUpdateDTO.class)
	public ResponseEntity<MemberDTO> updateMember(final @Validated @RequestBody MemberUpdateDTO memberUpdateDTO) {

		try {
			final MemberDTO responseBody = memberService.updateMember(memberUpdateDTO);

			return ResponseEntity.ok().body(responseBody);

		} catch (final MemberEntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	
	@DeleteMapping("/member/{id}")
	@ApiOperation(value = "Delete a member.")
	public ResponseEntity<?> deleteMember(final @PathVariable Integer id) {

		try {
			memberService.deleteMemberById(id);

			return ResponseEntity.ok().build();

		} catch (final MemberEntityNotFoundException e) {

			return ResponseEntity.notFound().build();
		}
	}
}
