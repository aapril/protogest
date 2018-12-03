package com.pfe.ldb.task.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pfe.ldb.task.dao.entity.EventEntity;

@Repository
public interface EventRepository extends CrudRepository<EventEntity, Integer> {
}
