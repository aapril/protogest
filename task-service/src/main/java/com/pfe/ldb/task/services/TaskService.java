package com.pfe.ldb.task.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfe.ldb.repositories.exceptions.TaskEntityNotFoundException;
import com.pfe.ldb.repositories.exceptions.TaskGroupEntityNotFoundException;
import com.pfe.ldb.task.models.TaskDTO;
import com.pfe.ldb.task.models.TaskGroupDTO;

@Service
@Transactional(readOnly = true)
public interface TaskService {

	public TaskDTO getTaskById(final Integer id) throws TaskEntityNotFoundException;

	public TaskGroupDTO getTaskGroupById(final Integer id) throws TaskGroupEntityNotFoundException;
	
	public List<TaskDTO> getTasksByTaskGroupId(final Integer taskGroupId) throws TaskGroupEntityNotFoundException;
	
	public TaskDTO createTask(final TaskDTO taskDTO);
	
	public TaskGroupDTO createTaskGroup(final TaskGroupDTO taskGroupDTO);

	public TaskDTO updateTask(final Integer id, final TaskDTO taskDTO) throws TaskEntityNotFoundException;

	public TaskGroupDTO updateTaskGroup(final Integer id, final TaskGroupDTO taskGroupDTO) throws TaskGroupEntityNotFoundException;

	public void deleteTaskById(final Integer id) throws TaskEntityNotFoundException;

	public void deleteTaskGroupById(final Integer id) throws TaskGroupEntityNotFoundException;

	public List<TaskGroupDTO> getTaskGroupsByEventId(final Integer eventId);
}
