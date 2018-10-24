package com.pfe.ldb.task.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.pfe.ldb.entities.TaskEntity;
import com.pfe.ldb.entities.TaskGroupEntity;
import com.pfe.ldb.task.models.TaskDTO;
import com.pfe.ldb.task.models.TaskGroupDTO;
import com.pfe.ldb.task.repositories.TaskGroupRepository;
import com.pfe.ldb.task.repositories.TaskRepository;
import com.pfe.ldb.task.repositories.exceptions.TaskEntityNotFoundException;
import com.pfe.ldb.task.repositories.exceptions.TaskGroupEntityNotFoundException;

public class DefaultTaskService implements TaskService {

	private @Autowired TaskRepository taskRepository;
	private @Autowired TaskGroupRepository taskGroupRepository;
	
	private @Autowired ModelMapper modelMapper;
	
	@Override
	public List<TaskGroupDTO> getTaskGroups() {
		
		final Iterable<TaskGroupEntity> taskGroupEntities = taskGroupRepository.findAll(); 
		
		return StreamSupport.stream(taskGroupEntities.spliterator(), true)
			.map(taskGroupEntity -> modelMapper.map(taskGroupEntity, TaskGroupDTO.class))
			.collect(Collectors.toList());
	}
	
	
	@Override
	public List<TaskDTO> getTasksFromTaskGroupId(final Integer taskGroupId) throws TaskGroupEntityNotFoundException {
		
		if(!taskGroupRepository.existsById(taskGroupId)) {
			throw new TaskGroupEntityNotFoundException();
		}
		
		final Iterable<TaskEntity> taskEntities = taskRepository.findByTaskGroupId(taskGroupId); 
		
		return StreamSupport.stream(taskEntities.spliterator(), true)
			.map(taskEntity -> modelMapper.map(taskEntity, TaskDTO.class))
			.collect(Collectors.toList());
	}


	@Transactional
	@Override
	public TaskDTO createTask(final TaskDTO taskDTO) {
		
		final TaskEntity taskEntityToSave = modelMapper.map(taskDTO, TaskEntity.class);
		final TaskEntity taskEntity = taskRepository.save(taskEntityToSave);
		
		return modelMapper.map(taskEntity, TaskDTO.class);
	}
	
	
	@Transactional
	@Override
	public TaskGroupDTO createTaskGroup(final TaskGroupDTO taskGroupDTO) {
		
		TaskGroupEntity taskGroupEntityToSave = modelMapper.map(taskGroupDTO, TaskGroupEntity.class);
		final TaskGroupEntity taskGroupEntity = taskGroupRepository.save(taskGroupEntityToSave);
		
		return modelMapper.map(taskGroupEntity, TaskGroupDTO.class);
	}


	@Transactional
	@Override
	public TaskDTO updateTask(final Integer id, final TaskDTO taskDTO) 
			throws TaskEntityNotFoundException {
		
		if(!taskRepository.existsById(id)) {
            throw new TaskEntityNotFoundException();
        }
        
		final TaskEntity taskEntityToSave = modelMapper.map(taskDTO, TaskEntity.class);
		final TaskEntity taskEntity = taskRepository.save(taskEntityToSave);
		
		return modelMapper.map(taskEntity, TaskDTO.class);
	}


	@Override
	public TaskGroupDTO updateTaskGroup(final Integer id, final TaskGroupDTO taskGroupDTO) 
			throws TaskGroupEntityNotFoundException {
		
		if(!taskGroupRepository.existsById(id)) {
            throw new TaskGroupEntityNotFoundException();
        }
		
		final TaskGroupEntity taskGroupEntityToSave = modelMapper.map(taskGroupDTO, TaskGroupEntity.class);
		final TaskGroupEntity taskGroupEntity = taskGroupRepository.save(taskGroupEntityToSave);
		
		return modelMapper.map(taskGroupEntity, TaskGroupDTO.class);
	}


	@Override
	public void deleteTaskById(final Integer id) throws TaskEntityNotFoundException {
		
		if(!taskRepository.existsById(id)) {
			throw new TaskEntityNotFoundException();
		}
	
		taskRepository.deleteById(id);
	}


	@Override
	public void deleteTaskGroupById(final Integer id) throws TaskGroupEntityNotFoundException {
		
		if(!taskGroupRepository.existsById(id)) {
			throw new TaskGroupEntityNotFoundException();
		}
	
		taskGroupRepository.deleteById(id);
	}
}
