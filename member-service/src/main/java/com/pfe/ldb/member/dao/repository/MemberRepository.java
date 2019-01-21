package com.pfe.ldb.member.dao.repository;

import java.util.Optional;

import model.MemberEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends CrudRepository<MemberEntity, Integer> {

	Optional<MemberEntity> findById(final Integer id);

	Optional<MemberEntity> findByUserId(final Integer id);
}
