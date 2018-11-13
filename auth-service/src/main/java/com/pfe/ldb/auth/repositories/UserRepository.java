package com.pfe.ldb.auth.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.pfe.ldb.entities.UserEntity;


@Transactional(readOnly = true)
public interface UserRepository extends  CrudRepository<UserEntity, Integer> {

	UserEntity findByUsername(final String username);

	boolean existsByUsername(final String username);

	void deleteByUsername(final String username);

}
