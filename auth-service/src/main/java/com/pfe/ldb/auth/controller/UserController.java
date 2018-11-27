package com.pfe.ldb.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.ldb.auth.dao.exception.InvalidUsernamePasswordException;
import com.pfe.ldb.auth.dao.exception.UserDoesntExistsException;
import com.pfe.ldb.auth.dao.exception.UsernameAlreadyExistsException;
import com.pfe.ldb.auth.dto.SignInDTO;
import com.pfe.ldb.auth.dto.SignUpDTO;
import com.pfe.ldb.auth.dto.UserDTO;
import com.pfe.ldb.auth.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/user/signIn")
	@ApiOperation(value = "Allow a user to signIn.", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Token"),
			@ApiResponse(code = 422, message = "Invalid username/password supplied.") })
	public ResponseEntity<UserDTO> signIn(final @RequestBody SignInDTO signInDTO) {

		try {
			final UserDTO responseBody = userService.signIn(signInDTO);

			return ResponseEntity.ok().body(responseBody);

		} catch (final InvalidUsernamePasswordException e) {
			return ResponseEntity.status(422).build();
		}
	}

	@PostMapping("/user/signUp")
	@ApiOperation(value = "Allow a user to signUp.", response = UserDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 422, message = "Username is already in use.") })
	public ResponseEntity<UserDTO> signup(final @RequestBody SignUpDTO signUpDTO) {

		try {
			final UserDTO responseBody = userService.signUp(signUpDTO);

			return ResponseEntity.ok().body(responseBody);

		} catch (final UsernameAlreadyExistsException e) {
			return ResponseEntity.status(422).build();
		}
	}

	@DeleteMapping(value = "/user/")
	@ApiOperation(value = "Delete a user account.")
	public ResponseEntity<?> delete(final @RequestParam Integer userId) {

		try {
			userService.deleteById(userId);

		} catch (final UserDoesntExistsException e) {
			return ResponseEntity.status(404).build();
		}

		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/user/")
	@ApiOperation(value = "Search a user by username.", response = UserDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 404, message = "The user doesn't exist.") })
	public ResponseEntity<UserDTO> search(final @RequestParam String username) {

		try {
			final UserDTO responseBody = userService.searchByUsername(username);

			return ResponseEntity.ok().body(responseBody);

		} catch (final UserDoesntExistsException e) {
			return ResponseEntity.status(404).build();
		}
	}
}
