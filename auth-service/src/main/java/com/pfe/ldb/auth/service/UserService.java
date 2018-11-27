package com.pfe.ldb.auth.service;

import org.springframework.stereotype.Service;

import com.pfe.ldb.auth.dao.exception.InvalidUsernamePasswordException;
import com.pfe.ldb.auth.dao.exception.UserDoesntExistsException;
import com.pfe.ldb.auth.dao.exception.UsernameAlreadyExistsException;
import com.pfe.ldb.auth.dto.SignInDTO;
import com.pfe.ldb.auth.dto.SignUpDTO;
import com.pfe.ldb.auth.dto.UserDTO;

@Service
public interface UserService {

	public UserDTO signIn(final SignInDTO signInDTO) throws InvalidUsernamePasswordException;

	public UserDTO signUp(final SignUpDTO signUpDTO) throws UsernameAlreadyExistsException;

	public void deleteById(final Integer id) throws UserDoesntExistsException;

	public UserDTO searchByUsername(final String username) throws UserDoesntExistsException;
}