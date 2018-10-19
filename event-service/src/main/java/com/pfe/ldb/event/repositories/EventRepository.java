package com.pfe.ldb.event.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.pfe.ldb.entities.EventEntity;


public interface EventRepository extends  CrudRepository<EventEntity,Integer> {

		EventEntity findByTaskIdAndEventGroupId(Integer taskId, Integer eventGroupId);
		List<EventEntity> findByEventGroupId(Integer eventGroupId);
		
}
