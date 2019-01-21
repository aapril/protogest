package com.pfe.ldb.task.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import model.EventEntity;
import model.TaskEntity;
import model.TaskGroupEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfe.ldb.task.dao.exception.EventEntityNotFoundException;
import com.pfe.ldb.task.dao.exception.TaskEntityNotFoundException;
import com.pfe.ldb.task.dao.exception.TaskGroupEntityNotFoundException;
import com.pfe.ldb.task.dao.repository.EventRepository;
import com.pfe.ldb.task.dao.repository.TaskGroupRepository;
import com.pfe.ldb.task.dao.repository.TaskRepository;
import com.pfe.ldb.task.dto.TaskCreateDTO;
import com.pfe.ldb.task.dto.TaskDTO;
import com.pfe.ldb.task.dto.TaskGroupCreateDTO;
import com.pfe.ldb.task.dto.TaskGroupDTO;
import com.pfe.ldb.task.dto.TaskGroupUpdateDTO;
import com.pfe.ldb.task.dto.TaskUpdateDTO;

@Service
@Transactional
public class DefaultTaskService implements TaskService {

	private @Autowired TaskRepository taskRepository;
	private @Autowired TaskGroupRepository taskGroupRepository;
	private @Autowired EventRepository eventRepository;

	private @Autowired ModelMapper modelMapper;


	@Override
	public TaskDTO getTaskById(final Integer id) throws TaskEntityNotFoundException {

		return modelMapper.map(findTaskEntityById(id), TaskDTO.class);
	}


	@Override
	public List<TaskDTO> getAllTasksByTaskGroupId(final Integer id)
		throws TaskGroupEntityNotFoundException {

		final List<TaskEntity> taskEntities = findTaskGroupEntityById(id).getTasks();

		return StreamSupport.stream(taskEntities.spliterator(), true)
				.map(taskEntity -> modelMapper.map(taskEntity, TaskDTO.class))
				.collect(Collectors.toList());
	}


	@Override
	public TaskGroupDTO getTaskGroupById(final Integer id) throws TaskGroupEntityNotFoundException {

		return modelMapper.map(findTaskGroupEntityById(id), TaskGroupDTO.class);
	}


	@Override
	public List<TaskGroupDTO> getAllTaskGroupsByEventId(final Integer id)
		throws EventEntityNotFoundException {

		final EventEntity eventEntity = findEventEntityById(id);
		final List<TaskGroupEntity> taskGroupEntities = eventEntity.getTaskGroups();

		return taskGroupEntities.stream()
				.map(taskGroupEntity -> modelMapper.map(taskGroupEntity, TaskGroupDTO.class))
				.collect(Collectors.toList());
	}


	@Override
	public TaskDTO createTask(final TaskCreateDTO dto) throws TaskGroupEntityNotFoundException {

		final TaskEntity toCreate = modelMapper.map(dto, TaskEntity.class);
		toCreate.setTaskGroup(findTaskGroupEntityById(dto.getTaskGroupId()));
		final TaskEntity saved = taskRepository.save(toCreate);

		return modelMapper.map(saved, TaskDTO.class);
	}


	@Override
	public TaskGroupDTO createTaskGroup(final TaskGroupCreateDTO dto)
		throws EventEntityNotFoundException {

		TaskGroupEntity toCreate = modelMapper.map(dto, TaskGroupEntity.class);
		toCreate.setEvent(findEventEntityById(dto.getEventId()));
		final TaskGroupEntity saved = taskGroupRepository.save(toCreate);

		return modelMapper.map(saved, TaskGroupDTO.class);
	}


	@Override
	public TaskDTO updateTask(final Integer id, final TaskUpdateDTO dto)
		throws TaskEntityNotFoundException,
		TaskGroupEntityNotFoundException {

		final TaskEntity toUpdate = findTaskEntityById(id);
		modelMapper.map(dto, toUpdate);

		if(!isSameTaskGroup(dto, toUpdate)) {
			toUpdate.setTaskGroup(findTaskGroupEntityById(dto.getTaskGroupId()));
		}

		final TaskEntity saved = taskRepository.save(toUpdate);

		return modelMapper.map(saved, TaskDTO.class);
	}


	@Override
	public TaskGroupDTO updateTaskGroup(final Integer id, final TaskGroupUpdateDTO dto)
		throws TaskGroupEntityNotFoundException,
		EventEntityNotFoundException {

		final TaskGroupEntity toUpdate = findTaskGroupEntityById(id);
		modelMapper.map(dto, toUpdate);

		if(!isSameEvent(dto, toUpdate)) {
			toUpdate.setEvent(findEventEntityById(dto.getEventId()));
		}

		final TaskGroupEntity saved = taskGroupRepository.save(toUpdate);

		return modelMapper.map(saved, TaskGroupDTO.class);
	}


	@Override
	public void deleteTaskById(final Integer id) throws TaskEntityNotFoundException {

		taskRepository.delete(findTaskEntityById(id));
	}


	@Override
	public void deleteTaskGroupById(final Integer id) throws TaskGroupEntityNotFoundException {

		taskGroupRepository.delete(findTaskGroupEntityById(id));
	}


	private TaskEntity findTaskEntityById(final Integer id) throws TaskEntityNotFoundException {

		final Optional<TaskEntity> taskEntity = taskRepository.findById(id);

		if(!taskEntity.isPresent()) {
			throw new TaskEntityNotFoundException();
		}

		return taskEntity.get();
	}


	private TaskGroupEntity findTaskGroupEntityById(final Integer id)
		throws TaskGroupEntityNotFoundException {

		final Optional<TaskGroupEntity> taskGroupEntity = taskGroupRepository.findById(id);

		if(!taskGroupEntity.isPresent()) {
			throw new TaskGroupEntityNotFoundException();
		}

		return taskGroupEntity.get();
	}


	private EventEntity findEventEntityById(final Integer id) throws EventEntityNotFoundException {

		final Optional<EventEntity> eventEntity = eventRepository.findById(id);

		if(!eventEntity.isPresent()) {
			throw new EventEntityNotFoundException();
		}

		return eventEntity.get();
	}


	private boolean isSameTaskGroup(final TaskUpdateDTO dto, final TaskEntity entity) {

		if(entity.getTaskGroup() == null) {
			return false;
		}

		return entity.getTaskGroup().getId().equals(dto.getTaskGroupId());
	}


	private boolean isSameEvent(final TaskGroupUpdateDTO dto, final TaskGroupEntity entity) {

		if(entity.getEvent() == null) {
			return false;
		}

		return entity.getEvent().getId().equals(dto.getEventId());
	}
}
