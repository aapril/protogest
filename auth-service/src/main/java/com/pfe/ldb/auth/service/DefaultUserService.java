package com.pfe.ldb.auth.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfe.ldb.auth.dao.entity.UserEntity;
import com.pfe.ldb.auth.dao.exception.InvalidUsernamePasswordException;
import com.pfe.ldb.auth.dao.exception.UserDoesntExistsException;
import com.pfe.ldb.auth.dao.exception.UsernameAlreadyExistsException;
import com.pfe.ldb.auth.dao.repository.UserRepository;
import com.pfe.ldb.auth.dto.SignInDTO;
import com.pfe.ldb.auth.dto.SignUpDTO;
import com.pfe.ldb.auth.dto.UserDTO;

@Transactional
@Service
public class DefaultUserService implements UserService {
	
	private @Autowired UserRepository userRepository;

	private @Autowired ModelMapper modelMapper;

	@Override
	public UserDTO signIn(final SignInDTO signInDTO) throws InvalidUsernamePasswordException {

		final UserEntity userEntity = userRepository.findByUsername(signInDTO.getUsername());

		if (!userEntity.getPassword().equals(signInDTO.getPassword())) {
			throw new InvalidUsernamePasswordException();
		}

		return modelMapper.map(userEntity, UserDTO.class);
	}

	@Override
	public UserDTO signUp(final SignUpDTO signUpDTO) throws UsernameAlreadyExistsException {

		if (userRepository.existsByUsername(signUpDTO.getUsername())) {
			throw new UsernameAlreadyExistsException();
		}

		final UserEntity userEntity = new UserEntity(signUpDTO.getUsername(), signUpDTO.getPassword());

		final UserEntity userEntitySaved = userRepository.save(userEntity);

		return modelMapper.map(userEntitySaved, UserDTO.class);
	}

	@Override
	public void deleteById(final Integer id) throws UserDoesntExistsException {

		if (!userRepository.existsById(id)) {
			throw new UserDoesntExistsException();
		}

		userRepository.deleteById(id);
	}

	@Override
	public UserDTO searchByUsername(final String username) throws UserDoesntExistsException {

		if (!userRepository.existsByUsername(username)) {
			throw new UserDoesntExistsException();
		}

		return modelMapper.map(userRepository.findByUsername(username), UserDTO.class);
	}
}