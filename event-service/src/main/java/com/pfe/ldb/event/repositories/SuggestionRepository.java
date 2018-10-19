package com.pfe.ldb.event.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.pfe.ldb.entities.SuggestionEntity;

public interface SuggestionRepository  extends CrudRepository<SuggestionEntity,Integer> {

	List<SuggestionEntity> findByEventUserDestinationId(Integer eventUserDestinationId);
	
}
