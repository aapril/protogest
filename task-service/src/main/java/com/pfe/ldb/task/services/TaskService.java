package com.pfe.ldb.task.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfe.ldb.repositories.exceptions.EventEntityNotFoundException;
import com.pfe.ldb.repositories.exceptions.TaskEntityNotFoundException;
import com.pfe.ldb.repositories.exceptions.TaskGroupEntityNotFoundException;
import com.pfe.ldb.task.models.TaskCreateDTO;
import com.pfe.ldb.task.models.TaskDTO;
import com.pfe.ldb.task.models.TaskGroupCreateDTO;
import com.pfe.ldb.task.models.TaskGroupDTO;
import com.pfe.ldb.task.models.TaskGroupUpdateDTO;
import com.pfe.ldb.task.models.TaskUpdateDTO;

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

	public List<TaskGroupDTO> getTaskGroupsByEventId(final Integer eventId) throws EventEntityNotFoundException;
}
