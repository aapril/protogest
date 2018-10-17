package com.pfe.ldb.member.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.pfe.ldb.entities.UserAuthoritiesEntity;

public interface RoleRepository extends CrudRepository<UserAuthoritiesEntity,Integer> {

	List<UserAuthoritiesEntity> findByUserId(Integer userId);

}
