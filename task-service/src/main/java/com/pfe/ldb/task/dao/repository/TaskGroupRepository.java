package com.pfe.ldb.task.dao.repository;

import java.util.List;

import model.TaskGroupEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskGroupRepository extends  CrudRepository<TaskGroupEntity, Integer> {

	List<TaskGroupEntity> findByEventId(final Integer eventId);

}
