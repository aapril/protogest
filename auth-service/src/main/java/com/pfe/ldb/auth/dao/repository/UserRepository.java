package com.pfe.ldb.auth.dao.repository;

import java.util.Optional;

import model.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

	Optional<UserEntity> findByUsername(final String username);

	boolean existsByUsername(final String username);
}
