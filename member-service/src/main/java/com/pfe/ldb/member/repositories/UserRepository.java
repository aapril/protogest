package com.pfe.ldb.member.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.pfe.ldb.entities.UserEntity;


@Transactional(readOnly = true)
public interface UserRepository extends  CrudRepository<UserEntity, Integer> {

	Optional<UserEntity> findById(final Integer id);
	
	boolean existsById(final Integer id);
}
