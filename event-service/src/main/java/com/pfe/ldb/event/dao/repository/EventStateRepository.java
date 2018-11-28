package com.pfe.ldb.event.dao.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pfe.ldb.event.dao.entity.EventStateEntity;

@Repository
public interface EventStateRepository extends CrudRepository<EventStateEntity, Integer> {

	Optional<EventStateEntity> findByName(final String name);
}
