package com.pfe.ldb.calendar.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.pfe.ldb.entities.TaskEntity;
import com.pfe.ldb.entities.UserEntity;

public interface TaskRepository extends  CrudRepository<TaskEntity,Integer> {

	List<TaskEntity> findByTaskGroupId(Integer taskGroupId);

}
