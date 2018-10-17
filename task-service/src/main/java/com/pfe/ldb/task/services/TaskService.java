package com.pfe.ldb.task.services;

import org.springframework.stereotype.Service;

import com.pfe.ldb.entities.TaskEntity;
import com.pfe.ldb.entities.TaskGroupEntity;

@Service
public interface TaskService {

	public Iterable<TaskEntity> getAllTasks();
	
	public Iterable<TaskGroupEntity> getAllTaskGroups();
	
	public Iterable<TaskEntity> getTasksFromTaskGroupId(final Integer taskGroupId);
}
