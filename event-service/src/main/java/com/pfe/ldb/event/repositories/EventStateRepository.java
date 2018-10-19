package com.pfe.ldb.event.repositories;

import org.springframework.data.repository.CrudRepository;

import com.pfe.ldb.entities.EventStateEntity;

public interface EventStateRepository extends  CrudRepository<EventStateEntity,Integer> {

	EventStateEntity findByName(String name);
}
