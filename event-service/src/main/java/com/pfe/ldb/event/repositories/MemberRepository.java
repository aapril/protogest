package com.pfe.ldb.event.repositories;

import org.springframework.data.repository.CrudRepository;

import com.pfe.ldb.entities.MemberEntity;

public interface MemberRepository extends CrudRepository<MemberEntity,Integer> {

	MemberEntity findByEmail(String email);
}
