package com.pfe.ldb.task.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.ldb.task.facades.TaskFacade;
import com.pfe.ldb.task.models.TaskCreateDto;
import com.pfe.ldb.task.models.TaskDto;
import com.pfe.ldb.task.models.TaskGroupCreateDto;
import com.pfe.ldb.task.models.TaskGroupDto;
import com.pfe.ldb.task.models.TaskGroupUpdateDto;
import com.pfe.ldb.task.models.TaskUpdateDto;

@RestController
public class TaskController {
	
	private @Autowired TaskFacade taskFacade;
	

	@GetMapping("/tasks")
	@ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<TaskDto> getTasks(final @RequestParam Optional<Integer> taskGroupId) {
		
		return taskFacade.getTasks(taskGroupId);
    }


	@GetMapping("/taskGroups")
	@ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<TaskGroupDto> getTaskGroups() {
		
		return taskFacade.getTaskGroups();
    }	
	
	
	@PostMapping("/tasks")
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody TaskDto createTask(final @RequestBody TaskCreateDto taskCreateDto) {
		
		return taskFacade.save(taskCreateDto);
	}
	
	
	@PostMapping("/taskGroups")
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody TaskGroupDto createTaskGroup(final @RequestBody TaskGroupCreateDto taskGroupCreateDto) {
		
		return taskFacade.save(taskGroupCreateDto);
	}
	
	
	@PutMapping("/tasks")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody TaskDto createTask(final @RequestBody TaskUpdateDto askUpdateDto) {
		
		return taskFacade.save(askUpdateDto);
	}
	
	
	@PutMapping("/taskGroups")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody TaskGroupDto createTaskGroup(final @RequestBody TaskGroupUpdateDto taskGroupUpdateDto) {
		
		return taskFacade.save(taskGroupUpdateDto);
	}
}
