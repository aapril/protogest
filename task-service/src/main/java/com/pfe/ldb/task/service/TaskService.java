package com.pfe.ldb.task.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfe.ldb.task.dao.exception.EventEntityNotFoundException;
import com.pfe.ldb.task.dao.exception.TaskEntityNotFoundException;
import com.pfe.ldb.task.dao.exception.TaskGroupEntityNotFoundException;
import com.pfe.ldb.task.dto.TaskCreateDTO;
import com.pfe.ldb.task.dto.TaskDTO;
import com.pfe.ldb.task.dto.TaskGroupCreateDTO;
import com.pfe.ldb.task.dto.TaskGroupDTO;
import com.pfe.ldb.task.dto.TaskGroupUpdateDTO;
import com.pfe.ldb.task.dto.TaskUpdateDTO;

@Service
@Transactional
public interface TaskService {

	public TaskDTO getTaskById(final Integer id) throws TaskEntityNotFoundException;


	public List<TaskDTO> getAllTasksByTaskGroupId(final Integer id)
		throws TaskGroupEntityNotFoundException;


	public TaskGroupDTO getTaskGroupById(final Integer id) throws TaskGroupEntityNotFoundException;


	public List<TaskGroupDTO> getAllTaskGroupsByEventId(final Integer id) throws EventEntityNotFoundException;


	public TaskDTO createTask(final TaskCreateDTO dto) throws TaskGroupEntityNotFoundException;


	public TaskGroupDTO createTaskGroup(final TaskGroupCreateDTO dto) throws EventEntityNotFoundException;


	public TaskDTO updateTask(final Integer id, final TaskUpdateDTO dto)
		throws TaskEntityNotFoundException, TaskGroupEntityNotFoundException;


	public TaskGroupDTO updateTaskGroup(final Integer id, final TaskGroupUpdateDTO dto)
		throws TaskGroupEntityNotFoundException, EventEntityNotFoundException;


	public void deleteTaskById(final Integer id) throws TaskEntityNotFoundException;


	public void deleteTaskGroupById(final Integer id) throws TaskGroupEntityNotFoundException;
}
