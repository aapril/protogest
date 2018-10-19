package com.pfe.ldb.task.facades;

import java.util.List;
import java.util.Optional;

import com.pfe.ldb.task.models.TaskDto;
import com.pfe.ldb.task.models.TaskGroupDto;

public interface TaskFacade {

	public List<TaskGroupDto> getTaskGroups();

	public List<TaskDto> getTasks(final Optional<Integer> taskGroupId);

	public TaskDto save(final TaskDto taskDto);

	public TaskGroupDto save(final TaskGroupDto taskGroupDto);
}
