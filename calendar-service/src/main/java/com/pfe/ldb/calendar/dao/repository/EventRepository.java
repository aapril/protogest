package com.pfe.ldb.calendar.dao.repository;

import java.util.List;

import model.EventEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<EventEntity, Integer> {

	List<EventEntity> findByEventGroupId(final Integer eventGroupId);
}
