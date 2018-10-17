package com.pfe.ldb.task.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.pfe.ldb.entities.TaskEntity;
import com.pfe.ldb.entities.TaskGroupEntity;
import com.pfe.ldb.task.repositories.TaskGroupRepository;
import com.pfe.ldb.task.repositories.TaskRepository;

public class DefaultTaskService implements TaskService {

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private TaskGroupRepository taskGroupRepository;
	
	@Override
	public Iterable<TaskEntity> getAllTasks() {
		return taskRepository.findAll();
	}

	@Override
	public Iterable<TaskGroupEntity> getAllTaskGroups() {
		return taskGroupRepository.findAll();
	}

	@Override
	public Iterable<TaskEntity> getTasksFromTaskGroupId(final Integer taskGroupId) {
		return taskRepository.findByTaskGroupId(taskGroupId);
	}

}
