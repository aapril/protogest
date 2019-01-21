package com.pfe.ldb.event.dao.repository;

import java.util.Optional;

import model.EventStateEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventStateRepository extends CrudRepository<EventStateEntity, Integer> {

	Optional<EventStateEntity> findByName(final String name);
}
