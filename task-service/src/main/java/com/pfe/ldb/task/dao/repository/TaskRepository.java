package com.pfe.ldb.task.dao.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pfe.ldb.task.dao.entity.TaskEntity;

@Repository
public interface TaskRepository extends  CrudRepository<TaskEntity, Integer> {

	List<TaskEntity> findByTaskGroupId(Integer taskGroupId);
}
