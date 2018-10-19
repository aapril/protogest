package com.pfe.ldb.event.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.pfe.ldb.entities.EventEntity;
import com.pfe.ldb.entities.EventUserDestinationEntity;

public interface EventUserDestinationRepository extends  CrudRepository<EventUserDestinationEntity,Integer> {

	EventUserDestinationEntity findByEventIdAndEmail(Integer eventId, String email);
	List<EventUserDestinationEntity> findByEventId(Integer eventId);
	List<EventUserDestinationEntity> findByMemberIdOrEmail(Integer memberId, String email);

}
