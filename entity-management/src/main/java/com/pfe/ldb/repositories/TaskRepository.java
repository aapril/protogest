package com.pfe.ldb.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.pfe.ldb.entities.TaskEntity;

@Transactional(readOnly = true)
public interface TaskRepository extends  CrudRepository<TaskEntity, Integer> {

	List<TaskEntity> findByTaskGroupId(Integer taskGroupId);
}
