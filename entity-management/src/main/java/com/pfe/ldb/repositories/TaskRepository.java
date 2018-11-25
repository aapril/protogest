package com.pfe.ldb.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pfe.ldb.entities.TaskEntity;

@Repository
public interface TaskRepository extends  CrudRepository<TaskEntity, Integer> {

	List<TaskEntity> findByTaskGroupId(Integer taskGroupId);
}
