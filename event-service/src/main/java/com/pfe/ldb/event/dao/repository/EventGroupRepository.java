package com.pfe.ldb.event.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pfe.ldb.event.dao.entity.EventGroupEntity;

@Repository
public interface EventGroupRepository extends CrudRepository<EventGroupEntity, Integer> {
}
