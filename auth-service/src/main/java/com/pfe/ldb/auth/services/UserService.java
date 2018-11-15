package com.pfe.ldb.auth.services;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import com.pfe.ldb.auth.models.AuthenticationDTO;
import com.pfe.ldb.auth.security.exceptions.InvalidUsernamePasswordException;
import com.pfe.ldb.auth.security.exceptions.UserDoesntExistsException;
import com.pfe.ldb.auth.security.exceptions.UsernameAlreadyExistsException;

@Service
public interface UserService {

	public String signin(final String username, final String password) throws InvalidUsernamePasswordException;

	public String signup(final AuthenticationDTO authentication) throws UsernameAlreadyExistsException;

	public void delete(final String username);

	public AuthenticationDTO search(final String username) throws UserDoesntExistsException;

	public AuthenticationDTO whoami(final HttpServletRequest req);

}