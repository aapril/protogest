package com.pfe.ldb.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.pfe.ldb.entities.TaskGroupEntity;

@Transactional(readOnly = true)
public interface TaskGroupRepository extends  CrudRepository<TaskGroupEntity,Integer> {

	List<TaskGroupEntity> findByEventId(final Integer eventId);

}
