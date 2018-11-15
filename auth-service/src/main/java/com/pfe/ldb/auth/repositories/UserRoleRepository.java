package com.pfe.ldb.auth.repositories;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.pfe.ldb.entities.AuthoritiesEntity;
import com.pfe.ldb.entities.UserAuthoritiesEntity;

@Transactional(readOnly = true)
public interface UserRoleRepository extends CrudRepository<UserAuthoritiesEntity, Integer> {

	Collection<AuthoritiesEntity> findByUserId(final Integer userId);
}
