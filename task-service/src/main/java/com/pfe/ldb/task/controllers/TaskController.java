package com.pfe.ldb.task.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.ldb.entities.TaskEntity;
import com.pfe.ldb.entities.TaskGroupEntity;
import com.pfe.ldb.task.models.TaskDto;
import com.pfe.ldb.task.models.TaskGroupDto;
import com.pfe.ldb.task.services.TaskService;

@RestController
public class TaskController {
	
	private @Autowired TaskService taskService;
	private@Autowired ModelMapper modelMapper;
	
	
	@RequestMapping("/tasks")
    public @ResponseBody List<TaskDto> getTasks(final @RequestParam Optional<Integer> taskGroupId) {
		
		final Iterable<TaskEntity> tasks = taskGroupId.isPresent() ?
				taskService.getTasksFromTaskGroupId(taskGroupId.get()) :
				taskService.getAllTasks();

		return StreamSupport.stream(tasks.spliterator(), true)
			.map(task -> modelMapper.map(task, TaskDto.class))
			.collect(Collectors.toList());
    }


	@RequestMapping("/taskGroups")
    public @ResponseBody List<TaskGroupDto> getTaskGroups() {
		
		final Iterable<TaskGroupEntity> taskGroups = taskService.getAllTaskGroups();
        
		return StreamSupport.stream(taskGroups.spliterator(), true)
				.map(taskGroup -> modelMapper.map(taskGroup, TaskGroupDto.class))
				.collect(Collectors.toList());
    }	
}
