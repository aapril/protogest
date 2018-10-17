package com.pfe.ldb.member.repository;

import org.springframework.data.repository.CrudRepository;

import com.pfe.ldb.entities.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity,Integer> {

	UserEntity findByUsername(String username);
}
