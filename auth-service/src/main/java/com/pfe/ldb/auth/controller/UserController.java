package com.pfe.ldb.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.ldb.auth.dao.exception.InvalidUsernamePasswordException;
import com.pfe.ldb.auth.dao.exception.UserEntityNotFoundException;
import com.pfe.ldb.auth.dao.exception.UsernameAlreadyExistsException;
import com.pfe.ldb.auth.dto.SignInDTO;
import com.pfe.ldb.auth.dto.SignUpDTO;
import com.pfe.ldb.auth.dto.UserDTO;
import com.pfe.ldb.auth.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = "http://protogest.com:4200")
public class UserController {

	private @Autowired UserService userService;


	@PostMapping("/user/signIn")
	@ApiOperation(value = "Allow a user to signIn.", response = String.class)
	@ApiResponses(
			value = { @ApiResponse(code = 200, message = "Token"),
					@ApiResponse(code = 422, message = "Invalid username/password supplied.") })
	public ResponseEntity<UserDTO> signIn(final @RequestBody SignInDTO dto) {

		try {
			return ResponseEntity.ok().body(userService.signIn(dto));

		} catch(final InvalidUsernamePasswordException e) {
			return ResponseEntity.status(422).build();

		} catch(final UserEntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}


	@PostMapping("/user/signUp")
	@ApiOperation(value = "Allow a user to signUp.", response = UserDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 422, message = "Username is already in use.") })
	public ResponseEntity<UserDTO> signup(final @RequestBody SignUpDTO dto) {

		try {
			return ResponseEntity.ok().body(userService.signUp(dto));

		} catch(final UsernameAlreadyExistsException e) {
			return ResponseEntity.status(422).build();
		}
	}


	@DeleteMapping(value = "/user/")
	@ApiOperation(value = "Delete a user account.")
	public ResponseEntity<?> delete(final @RequestParam Integer id) {

		try {
			userService.deleteById(id);

			return ResponseEntity.ok().build();

		} catch(final UserEntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}


	@GetMapping(value = "/user/")
	@ApiOperation(value = "Search a user by username.", response = UserDTO.class)
	public ResponseEntity<UserDTO> search(final @RequestParam String username) {

		try {
			return ResponseEntity.ok().body(userService.searchByUsername(username));

		} catch(final UserEntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
