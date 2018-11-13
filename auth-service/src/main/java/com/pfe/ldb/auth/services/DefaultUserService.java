package com.pfe.ldb.auth.services;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pfe.ldb.auth.repositories.RoleRepository;
import com.pfe.ldb.auth.repositories.UserRepository;
import com.pfe.ldb.auth.security.JwtTokenProvider;
import com.pfe.ldb.auth.security.exceptions.CustomException;
import com.pfe.ldb.entities.AuthoritiesEntity;
import com.pfe.ldb.entities.UserEntity;

@Service
public class DefaultUserService implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private AuthenticationManager authenticationManager;

	public String signin(final String username, final String password) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

			final UserEntity user = userRepository.findByUsername(username);
			final Collection<AuthoritiesEntity> roles = roleRepository.findByUserId(user.getId());

			return jwtTokenProvider.createToken(username, roles.stream().collect(Collectors.toList()));

		} catch (AuthenticationException e) {
			throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	public String signup(final UserEntity user) {
		if (!userRepository.existsByUsername(user.getUsername())) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userRepository.save(user);

			final Collection<AuthoritiesEntity> roles = roleRepository.findByUserId(user.getId());

			return jwtTokenProvider.createToken(user.getUsername(), roles.stream().collect(Collectors.toList()));
		} else {
			throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	public void delete(final String username) {

		userRepository.deleteByUsername(username);
	}

	public UserEntity search(final String username) {

		UserEntity user = userRepository.findByUsername(username);
		if (user == null) {
			throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
		}
		return user;
	}

	public UserEntity whoami(final HttpServletRequest req) {

		return userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
	}

}