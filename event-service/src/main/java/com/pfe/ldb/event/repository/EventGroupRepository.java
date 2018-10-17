package com.pfe.ldb.event.repository;

import org.springframework.data.repository.CrudRepository;

import com.pfe.ldb.entities.EventGroupEntity;

public interface EventGroupRepository extends  CrudRepository<EventGroupEntity,Integer> {

	EventGroupEntity findByName(String name);
}
