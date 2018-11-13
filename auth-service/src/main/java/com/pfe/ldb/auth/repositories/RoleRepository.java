package com.pfe.ldb.auth.repositories;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.pfe.ldb.entities.AuthoritiesEntity;

@Transactional(readOnly = true)
public interface RoleRepository extends  CrudRepository<AuthoritiesEntity, Integer> {

	Collection<AuthoritiesEntity> findByUserId(final Integer userId);

}
