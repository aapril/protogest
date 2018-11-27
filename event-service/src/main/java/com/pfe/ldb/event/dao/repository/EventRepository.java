package com.pfe.ldb.event.dao.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pfe.ldb.event.dao.entity.EventEntity;

@Repository
public interface EventRepository extends CrudRepository<EventEntity, Integer> {

	List<EventEntity> findByEventGroupId(final Integer eventGroupId);
}
