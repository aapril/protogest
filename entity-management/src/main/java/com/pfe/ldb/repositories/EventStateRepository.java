package com.pfe.ldb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pfe.ldb.entities.EventStateEntity;

@Repository
public interface EventStateRepository extends CrudRepository<EventStateEntity, Integer> {
}
