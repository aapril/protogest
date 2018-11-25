package com.pfe.ldb.auth.services;

import org.springframework.stereotype.Service;

import com.pfe.ldb.auth.models.SignInDTO;
import com.pfe.ldb.auth.models.SignUpDTO;
import com.pfe.ldb.auth.models.UserDTO;
import com.pfe.ldb.auth.services.exceptions.InvalidUsernamePasswordException;
import com.pfe.ldb.auth.services.exceptions.UserDoesntExistsException;
import com.pfe.ldb.auth.services.exceptions.UsernameAlreadyExistsException;

@Service
public interface UserService {

	public UserDTO signIn(final SignInDTO signInDTO) throws InvalidUsernamePasswordException;

	public UserDTO signUp(final SignUpDTO signUpDTO) throws UsernameAlreadyExistsException;

	public void deleteById(final Integer id) throws UserDoesntExistsException;

	public UserDTO searchByUsername(final String username) throws UserDoesntExistsException;
}