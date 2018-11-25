package com.pfe.ldb.auth.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfe.ldb.auth.models.SignInDTO;
import com.pfe.ldb.auth.models.SignUpDTO;
import com.pfe.ldb.auth.models.UserDTO;
import com.pfe.ldb.auth.services.exceptions.InvalidUsernamePasswordException;
import com.pfe.ldb.auth.services.exceptions.UserDoesntExistsException;
import com.pfe.ldb.auth.services.exceptions.UsernameAlreadyExistsException;
import com.pfe.ldb.entities.UserEntity;
import com.pfe.ldb.repositories.UserRepository;

@Service
@Transactional
public class DefaultUserService implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

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
	public UserDTO search(final String username) throws UserDoesntExistsException {

		if (!userRepository.existsByUsername(username)) {
			throw new UserDoesntExistsException();
		}

		return modelMapper.map(userRepository.findByUsername(username), UserDTO.class);
	}
}