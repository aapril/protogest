package com.pfe.ldb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.pfe.ldb.entities.EventStateEntity;

@Transactional(readOnly = true)
public interface EventStateRepository extends CrudRepository<EventStateEntity, Integer> {
}
