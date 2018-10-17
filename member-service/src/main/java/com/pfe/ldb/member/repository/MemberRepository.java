package com.pfe.ldb.member.repository;

import org.springframework.data.repository.CrudRepository;

import com.pfe.ldb.entities.MemberEntity;

public interface MemberRepository extends CrudRepository<MemberEntity,Integer> {

}
