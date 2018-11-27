package com.pfe.ldb.member.dao.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pfe.ldb.member.dao.entity.MemberEntity;

@Repository
public interface MemberRepository extends CrudRepository<MemberEntity, Integer> {

	Optional<MemberEntity> findById(final Integer id);

	Optional<MemberEntity> findByUserId(final Integer id);
}
