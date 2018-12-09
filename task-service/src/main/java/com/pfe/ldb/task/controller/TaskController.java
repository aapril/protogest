package com.pfe.ldb.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.ldb.task.dao.exception.TaskEntityNotFoundException;
import com.pfe.ldb.task.dao.exception.TaskGroupEntityNotFoundException;
import com.pfe.ldb.task.dto.TaskCreateDTO;
import com.pfe.ldb.task.dto.TaskDTO;
import com.pfe.ldb.task.dto.TaskGroupCreateDTO;
import com.pfe.ldb.task.dto.TaskGroupDTO;
import com.pfe.ldb.task.dto.TaskGroupUpdateDTO;
import com.pfe.ldb.task.dto.TaskUpdateDTO;
import com.pfe.ldb.task.service.TaskService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "http://protogest.com:4200")
public class TaskController {

	private @Autowired TaskService taskService;

	@GetMapping("/taskGroup/all")
	@ApiOperation(value = "Get a list of all task groups.", response = TaskGroupDTO.class, responseContainer = "List")
	public ResponseEntity<List<TaskGroupDTO>> getAllTasksGroups(final @RequestParam Integer eventId) {

		List<TaskGroupDTO> responseBody = taskService.getTaskGroupsByEventId(eventId);

		return ResponseEntity.ok().body(responseBody);
	}

	@GetMapping("/task/all")
	@ApiOperation(value = "Get a list of all tasks within a task group.", response = TaskDTO.class, responseContainer = "List")
	public ResponseEntity<List<TaskDTO>> getAllTasksByTaskGroupId(final @RequestParam Integer taskGroupId) {

		try {
			List<TaskDTO> responseBody = taskService.getTasksByTaskGroupId(taskGroupId);

			return ResponseEntity.ok().body(responseBody);

		} catch (final TaskGroupEntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/task/{id}")
	@ApiOperation(value = "Get a task.", response = TaskDTO.class)
	public ResponseEntity<TaskDTO> getTaskById(final @PathVariable Integer id) {

		try {
			final TaskDTO responseBody = taskService.getTaskById(id);

			return ResponseEntity.ok().body(responseBody);

		} catch (final TaskEntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/taskGroup/{id}")
	@ApiOperation(value = "Get a task group.", response = TaskGroupDTO.class)
	public ResponseEntity<TaskGroupDTO> getTaskGroupById(final @PathVariable Integer id) {

		try {
			final TaskGroupDTO responseBody = taskService.getTaskGroupById(id);

			return ResponseEntity.ok().body(responseBody);

		} catch (final TaskGroupEntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}

	}

	@PostMapping("/task")
	@ApiOperation(value = "Add a task to a group task.", response = TaskCreateDTO.class)
	public ResponseEntity<TaskDTO> createTask(final @Validated @RequestBody TaskCreateDTO taskCreateDTO) {

		final TaskDTO responseBody = taskService.createTask(taskCreateDTO);

		return ResponseEntity.ok().body(responseBody);
	}

	@PostMapping("/taskGroup")
	@ApiOperation(value = "Add a task group.", response = TaskGroupCreateDTO.class)
	public ResponseEntity<TaskGroupDTO> createTaskGroup(
			final @Validated @RequestBody TaskGroupCreateDTO taskGroupCreateDTO) {

		final TaskGroupDTO responseBody = taskService.createTaskGroup(taskGroupCreateDTO);

		return ResponseEntity.ok().body(responseBody);
	}

	@PutMapping("/task/{id}")
	@ApiOperation(value = "Update a task.", response = TaskUpdateDTO.class)
	public ResponseEntity<TaskDTO> updateTask(final @Validated @RequestBody TaskUpdateDTO taskUpdateDTO) {

		try {
			final TaskDTO responseBody = taskService.updateTask(taskUpdateDTO);

			return ResponseEntity.ok().body(responseBody);

		} catch (final TaskEntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/taskGroup/{id}")
	@ApiOperation(value = "Update a task group.", response = TaskGroupUpdateDTO.class)
	public ResponseEntity<TaskGroupDTO> updateTaskGroup(
			final @Validated @RequestBody TaskGroupUpdateDTO taskGroupUpdateDTO) {

		try {
			final TaskGroupDTO responseBody = taskService.updateTaskGroup(taskGroupUpdateDTO);

			return ResponseEntity.ok().body(responseBody);

		} catch (final TaskGroupEntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/task/{id}")
	@ApiOperation(value = "Delete a task.")
	public ResponseEntity<?> deleteTask(final @PathVariable Integer id) {

		try {
			taskService.deleteTaskById(id);

			return ResponseEntity.ok().build();

		} catch (final TaskEntityNotFoundException e) {

			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/taskGroup/{id}")
	@ApiOperation(value = "Delete a task group.")
	public ResponseEntity<?> deleteTaskGroup(final @PathVariable Integer id) {

		try {
			taskService.deleteTaskGroupById(id);

			return ResponseEntity.ok().build();

		} catch (final TaskGroupEntityNotFoundException e) {

			return ResponseEntity.notFound().build();
		}
	}
}
