package com.pfe.ldb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pfe.ldb.entities.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

	UserEntity findByUsername(final String username);

	boolean existsByUsername(final String username);

	void deleteByUsername(final String username);
}
