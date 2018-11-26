package com.pfe.ldb.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pfe.ldb.entities.MemberEntity;

@Repository
public interface MemberRepository extends CrudRepository<MemberEntity, Integer> {

	Optional<MemberEntity> findById(final Integer id);

	Optional<MemberEntity> findByUserId(final Integer id);
}
