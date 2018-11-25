package com.pfe.ldb.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.pfe.ldb.entities.EventEntity;

@Transactional(readOnly = true)
public interface EventRepository extends CrudRepository<EventEntity, Integer> {

	List<EventEntity> findByEventGroupId(final Integer eventGroupId);
}
