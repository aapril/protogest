package com.pfe.ldb.task.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.ldb.task.facades.TaskFacade;
import com.pfe.ldb.task.models.TaskDto;
import com.pfe.ldb.task.models.TaskGroupDto;

@RestController
public class TaskController {
	
	private @Autowired TaskFacade taskFacade;
	
	
	@RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public @ResponseBody List<TaskDto> getTasks(final @RequestParam Optional<Integer> taskGroupId) {
		
		return taskFacade.getTasks(taskGroupId);
    }


	@RequestMapping(value = "/taskGroups", method = RequestMethod.GET)
    public @ResponseBody List<TaskGroupDto> getTaskGroups() {
		
		return taskFacade.getTaskGroups();
    }	
	
	
	@RequestMapping(value = "/tasks", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody TaskDto createTask(final @RequestBody TaskDto taskDto) {
		
		return taskFacade.save(taskDto);
	}
	
	
	@RequestMapping(value = "/taskGroups", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody TaskGroupDto createTaskGroup(final @RequestBody TaskGroupDto taskGroupDto) {
		
		return taskFacade.save(taskGroupDto);
	}
}
