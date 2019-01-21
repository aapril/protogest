package com.pfe.ldb.event.dao.repository;

import model.EventGroupEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventGroupRepository extends CrudRepository<EventGroupEntity, Integer> {
}
