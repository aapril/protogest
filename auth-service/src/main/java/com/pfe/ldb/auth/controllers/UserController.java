package com.pfe.ldb.auth.controllers;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.ldb.auth.models.AuthenticationDTO;
import com.pfe.ldb.auth.security.exceptions.InvalidUsernamePasswordException;
import com.pfe.ldb.auth.security.exceptions.UserDoesntExistsException;
import com.pfe.ldb.auth.security.exceptions.UsernameAlreadyExistsException;
import com.pfe.ldb.auth.services.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class UserController {

  @Autowired
  private UserService userService;

  
  @PostMapping("/user/signin")
  @ApiOperation(value = "Allow a user to login.", response = String.class)
  @ApiResponses(value = {@ApiResponse(code = 422, message = "Invalid username/password supplied")})
  public ResponseEntity<String> login(final @RequestParam String username,
		  			  				  final @RequestParam String password) {
    
	try {
		final String responseBody = userService.signin(username, password);
		
		return ResponseEntity.ok().body(responseBody); 
		
	} catch (final InvalidUsernamePasswordException e) {
		return ResponseEntity.status(422).build();
	}
  }

  
  @PostMapping("/user/signup")
  @ApiOperation(value = "Allow a user to signup.", response = String.class)
  @ApiResponses(value = {@ApiResponse(code = 422, message = "Username is already in use")})
  public ResponseEntity<String> signup(final @RequestBody AuthenticationDTO authentiction) {
    
	try {
		final String responseBody = userService.signup(authentiction);
		
		return ResponseEntity.ok().body(responseBody); 
		
	} catch (UsernameAlreadyExistsException e) {
		return ResponseEntity.status(422).build();
	}
  }

  
  @DeleteMapping(value = "/user/")
  @ApiOperation(value = "Delete a user.")
  public ResponseEntity<?> delete(final @RequestParam String username) {
    
	userService.delete(username);
	
	return ResponseEntity.ok().build();
  }
  

  @GetMapping(value = "/user/")
  @ApiOperation(value = "Search a user by username.", response = AuthenticationDTO.class)
  @ApiResponses(value = {@ApiResponse(code = 404, message = "The user doesn't exist")})
  public ResponseEntity<AuthenticationDTO> search(final @RequestParam String username) {
	    
	try {
		final AuthenticationDTO responseBody = userService.search(username);
		
		return ResponseEntity.ok().body(responseBody); 
		
	} catch (UserDoesntExistsException e) {
		return ResponseEntity.status(404).build();
	}
  }

  
  @GetMapping(value = "/user/me")
  @ApiOperation(value = "Get info from the current user.", response = AuthenticationDTO.class)
  public ResponseEntity<AuthenticationDTO> whoami(final HttpServletRequest request) {
	  
    final AuthenticationDTO responseBody = userService.whoami(request);
	
	return ResponseEntity.ok().body(responseBody); 
  }
}
