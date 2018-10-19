package com.pfe.ldb.task.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.pfe.ldb.entities.TaskEntity;
import com.pfe.ldb.entities.TaskGroupEntity;
import com.pfe.ldb.task.repositories.TaskGroupRepository;
import com.pfe.ldb.task.repositories.TaskRepository;

public class DefaultTaskService implements TaskService {

	private @Autowired TaskRepository taskRepository;
	private @Autowired TaskGroupRepository taskGroupRepository;
	
	
	@Override
	public Iterable<TaskEntity> getTasks() {
		
		return taskRepository.findAll();
	}

	
	@Override
	public Iterable<TaskGroupEntity> getTaskGroups() {
		
		return taskGroupRepository.findAll();
	}

	
	@Override
	public Iterable<TaskEntity> getTasksFromTaskGroupId(final Integer taskGroupId) {
		
		return taskRepository.findByTaskGroupId(taskGroupId);
	}
	
	
	@Override
	public TaskEntity save(final TaskEntity taskEntity) {
		
		return taskRepository.save(taskEntity);
	}
	
	
	@Override
	public TaskGroupEntity save(final TaskGroupEntity taskGroupEntity) {
		
		return taskGroupRepository.save(taskGroupEntity);
	}
}
