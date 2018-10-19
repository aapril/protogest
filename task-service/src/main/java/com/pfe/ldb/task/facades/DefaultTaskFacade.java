package com.pfe.ldb.task.facades;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.pfe.ldb.entities.TaskEntity;
import com.pfe.ldb.entities.TaskGroupEntity;
import com.pfe.ldb.task.models.TaskDto;
import com.pfe.ldb.task.models.TaskGroupDto;
import com.pfe.ldb.task.services.TaskService;

public class DefaultTaskFacade implements TaskFacade {

	private @Autowired TaskService taskService;
	private@Autowired ModelMapper modelMapper;
	
	
	@Override
    public List<TaskDto> getTasks(final Optional<Integer> taskGroupId) {
		
		final Iterable<TaskEntity> taskEntities = taskGroupId.isPresent() ?
				taskService.getTasksFromTaskGroupId(taskGroupId.get()) :
				taskService.getTasks();

		return StreamSupport.stream(taskEntities.spliterator(), true)
			.map(taskEntity -> modelMapper.map(taskEntity, TaskDto.class))
			.collect(Collectors.toList());
    }
	

	@Override
    public List<TaskGroupDto> getTaskGroups() {
		
		final Iterable<TaskGroupEntity> taskGroupEntities = taskService.getTaskGroups();
        
		return StreamSupport.stream(taskGroupEntities.spliterator(), true)
				.map(taskGroupEntity -> modelMapper.map(taskGroupEntity, TaskGroupDto.class))
				.collect(Collectors.toList());
    }
	
	
	@Override
	public TaskDto save(final TaskDto taskDto) {
	
		final TaskEntity taskEntity = taskService.save(modelMapper.map(taskDto, TaskEntity.class));
		
		return modelMapper.map(taskEntity, TaskDto.class);
	}
	
	
	@Override
	public TaskGroupDto save(final TaskGroupDto taskGroupDto) {
	
		final TaskGroupEntity taskGroupEntity = taskService.save(modelMapper.map(taskGroupDto, TaskGroupEntity.class));
		
		return modelMapper.map(taskGroupEntity, TaskGroupDto.class);
	}
}
