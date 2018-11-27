package com.pfe.ldb.task.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfe.ldb.task.dao.exception.TaskEntityNotFoundException;
import com.pfe.ldb.task.dao.exception.TaskGroupEntityNotFoundException;
import com.pfe.ldb.task.dto.TaskCreateDTO;
import com.pfe.ldb.task.dto.TaskDTO;
import com.pfe.ldb.task.dto.TaskGroupCreateDTO;
import com.pfe.ldb.task.dto.TaskGroupDTO;
import com.pfe.ldb.task.dto.TaskGroupUpdateDTO;
import com.pfe.ldb.task.dto.TaskUpdateDTO;

@Service
@Transactional(readOnly = true)
public interface TaskService {

	public TaskDTO getTaskById(final Integer id) throws TaskEntityNotFoundException;

	public TaskGroupDTO getTaskGroupById(final Integer id) throws TaskGroupEntityNotFoundException;
	
	public List<TaskDTO> getTasksByTaskGroupId(final Integer taskGroupId) throws TaskGroupEntityNotFoundException;
	
	public TaskDTO createTask(final TaskCreateDTO taskCreateDTO);
	
	public TaskGroupDTO createTaskGroup(final TaskGroupCreateDTO taskGroupCreateDTO);

	public TaskDTO updateTask(final TaskUpdateDTO taskUpdateDTO) throws TaskEntityNotFoundException;

	public TaskGroupDTO updateTaskGroup(final TaskGroupUpdateDTO taskGroupUpdateDTO) throws TaskGroupEntityNotFoundException;

	public void deleteTaskById(final Integer id) throws TaskEntityNotFoundException;

	public void deleteTaskGroupById(final Integer id) throws TaskGroupEntityNotFoundException;

	public List<TaskGroupDTO> getTaskGroupsByEventId(final Integer eventId);
}
