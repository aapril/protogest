package com.pfe.ldb.task.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.pfe.ldb.entities.TaskEntity;

public interface TaskRepository extends  CrudRepository<TaskEntity, Integer> {

	List<TaskEntity> findByTaskGroupId(Integer taskGroupId);
}
