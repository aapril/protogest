package com.pfe.ldb.task.facades;

import java.util.List;
import java.util.Optional;

import com.pfe.ldb.task.models.TaskCreateDto;
import com.pfe.ldb.task.models.TaskDto;
import com.pfe.ldb.task.models.TaskGroupCreateDto;
import com.pfe.ldb.task.models.TaskGroupDto;
import com.pfe.ldb.task.models.TaskGroupUpdateDto;
import com.pfe.ldb.task.models.TaskUpdateDto;

public interface TaskFacade {

	public List<TaskGroupDto> getTaskGroups();

	public List<TaskDto> getTasks(final Optional<Integer> taskGroupId);

	public TaskDto save(final TaskCreateDto taskCreateDto);

	public TaskGroupDto save(final TaskGroupCreateDto taskGroupCreateDto);

	public TaskDto save(final TaskUpdateDto taskUpdateDto);
	
	public TaskGroupDto save(final TaskGroupUpdateDto taskGroupUpdateDto);
}
