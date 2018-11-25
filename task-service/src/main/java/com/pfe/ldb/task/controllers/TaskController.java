package com.pfe.ldb.task.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.ldb.repositories.exceptions.TaskEntityNotFoundException;
import com.pfe.ldb.repositories.exceptions.TaskGroupEntityNotFoundException;
import com.pfe.ldb.task.models.TaskDTO;
import com.pfe.ldb.task.models.TaskGroupDTO;
import com.pfe.ldb.task.services.TaskService;

import io.swagger.annotations.ApiOperation;

@RestController
public class TaskController {
	
	private @Autowired TaskService taskService;
	
	
	@GetMapping("/taskGroup/all")
	@ApiOperation(value = "Get a list of all task groups.", response = TaskGroupDTO.class, responseContainer = "List")
    public ResponseEntity<List<TaskGroupDTO>> getAllTasksGroups(final @PathVariable Integer eventId) {
		
		final List<TaskGroupDTO> responseBody = taskService.getTaskGroupsByEventId(eventId);
		
		return ResponseEntity.ok().body(responseBody);
    }
	
	
	@GetMapping("/task/all")
	@ApiOperation(value = "Get a list of all tasks within a task group.", response = TaskDTO.class, responseContainer = "List")
    public ResponseEntity<List<TaskDTO>> getAllTasksByTaskGroupId(
    		final @RequestParam Integer taskGroupId) {
		
		try {
			List<TaskDTO> responseBody = taskService.getTasksByTaskGroupId(taskGroupId);
			
			return ResponseEntity.ok().body(responseBody); 
			
		} catch (TaskGroupEntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
    }

	
	@GetMapping("/task/{id}")
	@ApiOperation(value = "Get a task group.", response = TaskDTO.class)
    public ResponseEntity<TaskDTO> getTaskById(final @PathVariable Integer id) {
		
		try {
			final TaskDTO responseBody= taskService.getTaskById(id);
			
			return ResponseEntity.ok().body(responseBody);
			
		} catch (TaskEntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
    }
	
	
	@GetMapping("/taskGroup/{id}")
	@ApiOperation(value = "Get a task group.", response = TaskGroupDTO.class)
    public ResponseEntity<TaskGroupDTO> getTaskGroupById(final @PathVariable Integer id) {
		
		try {
			final TaskGroupDTO responseBody = taskService.getTaskGroupById(id);

			return ResponseEntity.ok().body(responseBody);
		
		} catch (TaskGroupEntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
		
    }
	
	
	@PostMapping("/task")
	@ApiOperation(value = "Add a task to a group task.", response = TaskDTO.class)
	public ResponseEntity<TaskDTO> createTask(
			final @Validated @RequestBody TaskDTO taskDTO) {
		
		final TaskDTO responseBody = taskService.createTask(taskDTO);
		
		return ResponseEntity.ok().body(responseBody);
	}
	
	
	@PostMapping("/taskGroup")
	@ApiOperation(value = "Add a task group.", response = TaskGroupDTO.class)
	public ResponseEntity<TaskGroupDTO> createTaskGroup(
			final @Validated @RequestBody TaskGroupDTO taskGroupDTO) {
		
		final TaskGroupDTO responseBody = taskService.createTaskGroup(taskGroupDTO);
		
		return ResponseEntity.ok().body(responseBody);
	}
	
	
	@PutMapping("/task/{id}")
	@ApiOperation(value = "Update a task.", response = TaskDTO.class)
	public ResponseEntity<TaskDTO> updateTask(
			final @PathVariable Integer id, 
			final @RequestBody TaskDTO taskDTO) {
		
		try {
			final TaskDTO responseBody = taskService.updateTask(id, taskDTO);
			
			return ResponseEntity.ok().body(responseBody);
			
		} catch (TaskEntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}	
	}
	
	
	@PutMapping("/taskGroup/{id}")
	@ApiOperation(value = "Update a task group.", response = TaskGroupDTO.class)
	public ResponseEntity<TaskGroupDTO> updateTaskGroup(
			final @PathVariable Integer id, 
			final @RequestBody TaskGroupDTO taskGroupDTO) {
		
		try {
			final TaskGroupDTO responseBody = taskService.updateTaskGroup(id, taskGroupDTO);
			
			return ResponseEntity.ok().body(responseBody);
			
		} catch (TaskGroupEntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	@DeleteMapping("/task/{id}")
	@ApiOperation(value = "Delete a task.")
	public ResponseEntity<?> deleteTask(final @PathVariable Integer id) {
			
		try {
			taskService.deleteTaskById(id);
			
			return ResponseEntity.ok().build();
			
		} catch (TaskEntityNotFoundException e) {
			
			return ResponseEntity.notFound().build();
		}
	}
	
	
	@DeleteMapping("/taskGroup/{id}")
	@ApiOperation(value = "Delete a task group.")
	public ResponseEntity<?> deleteTaskGroup(final @PathVariable Integer id) {
			
		try {
			taskService.deleteTaskGroupById(id);
			
			return ResponseEntity.ok().build();
			
		} catch (TaskGroupEntityNotFoundException e) {
			
			return ResponseEntity.notFound().build();
		}
	}
}
