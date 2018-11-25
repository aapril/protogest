package com.pfe.ldb.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.pfe.ldb.entities.MemberEntity;

@Transactional(readOnly = true)
public interface MemberRepository extends CrudRepository<MemberEntity, Integer> {

	Optional<MemberEntity> findById(final Integer id);
	
	boolean existsById(final Integer id);
}
