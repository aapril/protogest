package com.pfe.ldb.task.services;

import org.springframework.stereotype.Service;

import com.pfe.ldb.entities.TaskEntity;
import com.pfe.ldb.entities.TaskGroupEntity;
import com.pfe.ldb.task.models.TaskDto;

@Service
public interface TaskService {

	public Iterable<TaskEntity> getTasks();
	
	public Iterable<TaskGroupEntity> getTaskGroups();
	
	public Iterable<TaskEntity> getTasksFromTaskGroupId(final Integer taskGroupId);

	public TaskEntity save(final TaskEntity task);

	public TaskGroupEntity save(final TaskGroupEntity taskGroupEntity);
}
