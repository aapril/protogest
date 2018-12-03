package com.pfe.ldb.auth.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfe.ldb.auth.dao.entity.UserEntity;
import com.pfe.ldb.auth.dao.exception.InvalidUsernamePasswordException;
import com.pfe.ldb.auth.dao.exception.UserEntityNotFoundException;
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
	public UserDTO signIn(final SignInDTO dto)
		throws InvalidUsernamePasswordException,
		UserEntityNotFoundException {

		final UserEntity toSignIn = findUserEntityByUsername(dto.getUsername());

		if(isSamePassword(dto, toSignIn)) {
			throw new InvalidUsernamePasswordException();
		}

		return modelMapper.map(toSignIn, UserDTO.class);
	}


	@Override
	public UserDTO signUp(final SignUpDTO dto) throws UsernameAlreadyExistsException {

		if(userRepository.existsByUsername(dto.getUsername())) {
			throw new UsernameAlreadyExistsException();
		}

		final UserEntity toSignUp = new UserEntity(dto.getUsername(), dto.getPassword());
		final UserEntity userEntitySaved = userRepository.save(toSignUp);

		return modelMapper.map(userEntitySaved, UserDTO.class);
	}


	@Override
	public void deleteById(final Integer id) throws UserEntityNotFoundException {

		userRepository.delete(findUserEntityById(id));
	}


	@Override
	public UserDTO searchByUsername(final String username) throws UserEntityNotFoundException {

		return modelMapper.map(findUserEntityByUsername(username), UserDTO.class);
	}


	private UserEntity findUserEntityByUsername(final String username)
		throws UserEntityNotFoundException {

		final Optional<UserEntity> userEntity = userRepository.findByUsername(username);

		if(!userEntity.isPresent()) {
			throw new UserEntityNotFoundException();
		}

		return userEntity.get();
	}


	private UserEntity findUserEntityById(final Integer id)
		throws UserEntityNotFoundException {

		final Optional<UserEntity> userEntity = userRepository.findById(id);

		if(!userEntity.isPresent()) {
			throw new UserEntityNotFoundException();
		}

		return userEntity.get();
	}


	private boolean isSamePassword(final SignInDTO dto, final UserEntity toSignIn) {

		return !toSignIn.getPassword().equals(dto.getPassword());
	}
}