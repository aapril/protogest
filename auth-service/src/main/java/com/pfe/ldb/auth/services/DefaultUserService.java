package com.pfe.ldb.auth.services;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pfe.ldb.auth.models.AuthenticationDTO;
import com.pfe.ldb.auth.repositories.UserRepository;
import com.pfe.ldb.auth.repositories.UserRoleRepository;
import com.pfe.ldb.auth.security.JwtProvider;
import com.pfe.ldb.auth.security.exceptions.InvalidUsernamePasswordException;
import com.pfe.ldb.auth.security.exceptions.UserDoesntExistsException;
import com.pfe.ldb.auth.security.exceptions.UsernameAlreadyExistsException;
import com.pfe.ldb.entities.AuthoritiesEntity;
import com.pfe.ldb.entities.UserEntity;

@Service
public class DefaultUserService implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtProvider jwtTokenProvider;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private ModelMapper modelMapper;

	public String signin(final String username, final String password) throws InvalidUsernamePasswordException {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

			final UserEntity user = userRepository.findByUsername(username);
			final Collection<AuthoritiesEntity> roles = userRoleRepository.findByUserId(user.getId());

			return jwtTokenProvider.createToken(username, roles.stream().collect(Collectors.toList()));

		} catch (AuthenticationException e) {
			throw new InvalidUsernamePasswordException("Invalid username/password supplied",
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	public String signup(final AuthenticationDTO authentication) throws UsernameAlreadyExistsException {
		final UserEntity userEntity = modelMapper.map(authentication, UserEntity.class);

		if (userRepository.existsByUsername(userEntity.getUsername())) {
			throw new UsernameAlreadyExistsException("Username is already in use.", HttpStatus.UNPROCESSABLE_ENTITY);
		}

		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
		userRepository.save(userEntity);

		final Collection<AuthoritiesEntity> roles = userRoleRepository.findByUserId(userEntity.getId());

		return jwtTokenProvider.createToken(userEntity.getUsername(), roles.stream().collect(Collectors.toList()));
	}

	public void delete(final String username) {

		userRepository.deleteByUsername(username);
	}

	public AuthenticationDTO search(final String username) throws UserDoesntExistsException {

		if (!userRepository.existsByUsername(username)) {
			throw new UserDoesntExistsException("The user doesn't exist", HttpStatus.NOT_FOUND);
		}

		final UserEntity userEntity = userRepository.findByUsername(username);

		return modelMapper.map(userEntity, AuthenticationDTO.class);
	}

	public AuthenticationDTO whoami(final HttpServletRequest request) {

		final String username = jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(request));

		final UserEntity userEntity = userRepository.findByUsername(username);

		return modelMapper.map(userEntity, AuthenticationDTO.class);
	}
}