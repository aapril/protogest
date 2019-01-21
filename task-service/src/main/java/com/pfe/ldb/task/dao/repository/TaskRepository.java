package com.pfe.ldb.task.dao.repository;

import java.util.List;

import model.TaskEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends  CrudRepository<TaskEntity, Integer> {

	List<TaskEntity> findByTaskGroupId(Integer taskGroupId);
}
