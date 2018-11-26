package com.pfe.ldb.task.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfe.ldb.entities.EventEntity;
import com.pfe.ldb.entities.TaskEntity;
import com.pfe.ldb.entities.TaskGroupEntity;
import com.pfe.ldb.repositories.EventRepository;
import com.pfe.ldb.repositories.TaskGroupRepository;
import com.pfe.ldb.repositories.TaskRepository;
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
@Transactional
public class DefaultTaskService implements TaskService {

	private @Autowired TaskRepository taskRepository;
	private @Autowired TaskGroupRepository taskGroupRepository;
	private @Autowired EventRepository eventRepository;

	private @Autowired ModelMapper modelMapper;

	@Override
	public TaskDTO getTaskById(final Integer id) throws TaskEntityNotFoundException {

		final Optional<TaskEntity> taskEntity = taskRepository.findById(id);

		if (!taskEntity.isPresent()) {
			throw new TaskEntityNotFoundException();
		}

		return modelMapper.map(taskEntity.get(), TaskDTO.class);
	}

	@Override
	public TaskGroupDTO getTaskGroupById(final Integer id) throws TaskGroupEntityNotFoundException {

		final Optional<TaskGroupEntity> taskGroupEntity = taskGroupRepository.findById(id);

		if (!taskGroupEntity.isPresent()) {
			throw new TaskGroupEntityNotFoundException();
		}

		return modelMapper.map(taskGroupEntity.get(), TaskGroupDTO.class);
	}

	@Override
	public List<TaskDTO> getTasksByTaskGroupId(final Integer taskGroupId) throws TaskGroupEntityNotFoundException {

		final Optional<TaskGroupEntity> taskGroupEntity = taskGroupRepository.findById(taskGroupId);

		if (!taskGroupEntity.isPresent()) {
			throw new TaskGroupEntityNotFoundException();
		}

		final List<TaskEntity> taskEntities = taskGroupEntity.get().getTasks();

		return StreamSupport.stream(taskEntities.spliterator(), true)
				.map(taskEntity -> modelMapper.map(taskEntity, TaskDTO.class)).collect(Collectors.toList());
	}

	@Override
	public TaskDTO createTask(final TaskCreateDTO taskCreateDTO) {

		final TaskEntity toSave = modelMapper.map(taskCreateDTO, TaskEntity.class);
		final TaskEntity saved = taskRepository.save(toSave);

		return modelMapper.map(saved, TaskDTO.class);
	}

	@Override
	public TaskGroupDTO createTaskGroup(final TaskGroupCreateDTO taskGroupCreateDTO) {

		TaskGroupEntity toSave = modelMapper.map(taskGroupCreateDTO, TaskGroupEntity.class);
		final TaskGroupEntity saved = taskGroupRepository.save(toSave);

		return modelMapper.map(saved, TaskGroupDTO.class);
	}

	@Override
	public TaskDTO updateTask(final TaskUpdateDTO taskUpdateDTO) throws TaskEntityNotFoundException {

		final Optional<TaskEntity> taskEntity = taskRepository.findById(taskUpdateDTO.getId());

		if (!taskEntity.isPresent()) {
			throw new TaskEntityNotFoundException();
		}

		final TaskEntity toUpdate = taskEntity.get();
		modelMapper.map(taskUpdateDTO, toUpdate);
		final TaskEntity saved = taskRepository.save(toUpdate);

		return modelMapper.map(saved, TaskDTO.class);
	}

	@Override
	public TaskGroupDTO updateTaskGroup(final TaskGroupUpdateDTO taskGroupUpdateDTO)
			throws TaskGroupEntityNotFoundException {

		final Optional<TaskGroupEntity> taskGroupEntity = taskGroupRepository.findById(taskGroupUpdateDTO.getId());

		if (!taskGroupEntity.isPresent()) {
			throw new TaskGroupEntityNotFoundException();
		}

		final TaskGroupEntity toUpdate = taskGroupEntity.get();
		modelMapper.map(taskGroupUpdateDTO, toUpdate);
		final TaskGroupEntity saved = taskGroupRepository.save(toUpdate);

		return modelMapper.map(saved, TaskGroupDTO.class);
	}

	@Override
	public void deleteTaskById(final Integer id) throws TaskEntityNotFoundException {

		final Optional<TaskEntity> taskEntity = taskRepository.findById(id);

		if (!taskEntity.isPresent()) {
			throw new TaskEntityNotFoundException();
		}

		taskRepository.delete(taskEntity.get());
	}

	@Override
	public void deleteTaskGroupById(final Integer id) throws TaskGroupEntityNotFoundException {

		final Optional<TaskGroupEntity> taskGroupEntity = taskGroupRepository.findById(id);

		if (!taskGroupEntity.isPresent()) {
			throw new TaskGroupEntityNotFoundException();
		}

		taskGroupRepository.delete(taskGroupEntity.get());
	}

	@Override
	public List<TaskGroupDTO> getTaskGroupsByEventId(final Integer eventId) throws EventEntityNotFoundException {

		final Optional<EventEntity> eventEntity = eventRepository.findById(eventId);

		if (!eventEntity.isPresent()) {
			throw new EventEntityNotFoundException();
		}

		final List<TaskGroupEntity> taskGroupEntities = eventEntity.get().getTaskGroup();

		return taskGroupEntities.stream().map(taskGroupEntity -> modelMapper.map(taskGroupEntity, TaskGroupDTO.class))
				.collect(Collectors.toList());
	}
}
