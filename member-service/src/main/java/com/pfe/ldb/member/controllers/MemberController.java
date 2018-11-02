package com.pfe.ldb.member.controllers;

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

import com.pfe.ldb.member.models.MemberDTO;
import com.pfe.ldb.member.repositories.exceptions.MemberEntityNotFoundException;
import com.pfe.ldb.member.repositories.exceptions.UserEntityNotFoundException;
import com.pfe.ldb.member.services.MemberService;

import io.swagger.annotations.ApiOperation;

@RestController
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

		} catch (MemberEntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	
	@GetMapping("/member/")
	@ApiOperation(value = "Get a member by a user id.", response = MemberDTO.class)
	public ResponseEntity<MemberDTO> getMemberByUserId(final @RequestParam Integer userId) {

		try {
			final MemberDTO responseBody = memberService.getMemberByUserId(userId);

			return ResponseEntity.ok().body(responseBody);

		} catch (UserEntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	
	@PostMapping("/member")
	@ApiOperation(value = "Add a member.", response = MemberDTO.class)
	public ResponseEntity<MemberDTO> createMember(final @Validated @RequestBody MemberDTO taskDTO) {

		final MemberDTO responseBody = memberService.createMember(taskDTO);

		return ResponseEntity.ok().body(responseBody);
	}

	
	@PutMapping("/member/{id}")
	@ApiOperation(value = "Update a member.", response = MemberDTO.class)
	public ResponseEntity<MemberDTO> updateMember(final @PathVariable Integer id,
			final @RequestBody MemberDTO taskDTO) {

		try {
			final MemberDTO responseBody = memberService.updateMember(id, taskDTO);

			return ResponseEntity.ok().body(responseBody);

		} catch (MemberEntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	
	@DeleteMapping("/member/{id}")
	@ApiOperation(value = "Delete a member.")
	public ResponseEntity<?> deleteMember(final @PathVariable Integer id) {

		try {
			memberService.deleteMemberById(id);

			return ResponseEntity.ok().build();

		} catch (MemberEntityNotFoundException e) {

			return ResponseEntity.notFound().build();
		}
	}
}