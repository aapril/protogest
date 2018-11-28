package com.pfe.ldb.event.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfe.ldb.event.dao.entity.EventEntity;
import com.pfe.ldb.event.dao.entity.EventGroupEntity;
import com.pfe.ldb.event.dao.entity.EventStateEntity;
import com.pfe.ldb.event.dao.exception.EventEntityNotFoundException;
import com.pfe.ldb.event.dao.exception.EventGroupEntityNotFoundException;
import com.pfe.ldb.event.dao.exception.EventStateEntityNotFoundException;
import com.pfe.ldb.event.dao.repository.EventGroupRepository;
import com.pfe.ldb.event.dao.repository.EventRepository;
import com.pfe.ldb.event.dao.repository.EventStateRepository;
import com.pfe.ldb.event.dto.EventCreateDTO;
import com.pfe.ldb.event.dto.EventDTO;
import com.pfe.ldb.event.dto.EventGroupCreateDTO;
import com.pfe.ldb.event.dto.EventGroupDTO;
import com.pfe.ldb.event.dto.EventGroupUpdateDTO;
import com.pfe.ldb.event.dto.EventStateDTO;
import com.pfe.ldb.event.dto.EventUpdateDTO;

@Transactional
@Service
public class DefaultEventService implements EventService {

	private static final String EVENT_STATE_PENDING = "PENDING";

	private @Autowired EventRepository eventRepository;
	private @Autowired EventGroupRepository eventGroupRepository;
	private @Autowired EventStateRepository eventStateRepository;

	private @Autowired ModelMapper modelMapper;


	@Override
	public EventDTO getEventById(final Integer id) throws EventEntityNotFoundException {

		return modelMapper.map(findEventEntityById(id), EventDTO.class);
	}


	@Override
	public List<EventDTO> getAllEventsByEventGroupId(final Integer eventGroupId)
			throws EventGroupEntityNotFoundException {

		final EventGroupEntity eventGroupEntity = findEventGroupEntityById(eventGroupId);
		final List<EventEntity> eventEntities = eventGroupEntity.getEvents();

		return eventEntities.stream()
				.map(eventEntity -> modelMapper.map(eventEntity, EventDTO.class))
				.collect(Collectors.toList());
	}


	@Override
	public List<EventDTO> getAllEventsByCurrentUser() {

		// TODO : Change logic to fetch event of the current user.

		final Iterable<EventEntity> eventEntities = eventRepository.findAll();

		return StreamSupport.stream(eventEntities.spliterator(), true)
				.map(eventEntity -> modelMapper.map(eventEntity, EventDTO.class))
				.collect(Collectors.toList());
	}


	@Override
	public EventGroupDTO getEventGroupById(final Integer id)
			throws EventGroupEntityNotFoundException {

		return modelMapper.map(findEventGroupEntityById(id), EventGroupDTO.class);
	}


	@Override
	public List<EventGroupDTO> getAllEventGroups() {

		final Iterable<EventGroupEntity> eventGroupEntities = eventGroupRepository.findAll();

		return StreamSupport.stream(eventGroupEntities.spliterator(), true)
				.map(eventGroupEntity -> modelMapper.map(eventGroupEntity, EventGroupDTO.class))
				.collect(Collectors.toList());
	}


	@Override
	public EventDTO createEvent(final EventCreateDTO eventCreateDTO)
			throws EventGroupEntityNotFoundException, EventStateEntityNotFoundException {

		final Integer eventGroupId = eventCreateDTO.getEventGroupId();
		final EventGroupEntity eventGroupEntity = findEventGroupEntityById(eventGroupId);
		final EventStateEntity eventStateEntity = findEventStateEntityByName(EVENT_STATE_PENDING);

		final EventEntity toCreate = modelMapper.map(eventCreateDTO, EventEntity.class);
		toCreate.setEventGroup(eventGroupEntity);
		toCreate.setEventState(eventStateEntity);
		final EventEntity saved = eventRepository.save(toCreate);

		return modelMapper.map(saved, EventDTO.class);
	}


	@Override
	public EventGroupDTO createEventGroup(final EventGroupCreateDTO eventGroupCreateDTO) {

		final EventGroupEntity toCreate = modelMapper.map(eventGroupCreateDTO,
				EventGroupEntity.class);
		final EventGroupEntity saved = eventGroupRepository.save(toCreate);

		return modelMapper.map(saved, EventGroupDTO.class);
	}


	@Override
	public EventDTO updateEvent(final Integer id,
								final EventUpdateDTO eventUpdateDTO)
			throws EventEntityNotFoundException, EventGroupEntityNotFoundException,
			EventStateEntityNotFoundException {

		final EventEntity toUpdate = findEventEntityById(id);
		modelMapper.map(eventUpdateDTO, toUpdate);

		if(!isSameEventGroup(eventUpdateDTO, toUpdate)) {
			toUpdate.setEventGroup(findEventGroupEntityById(eventUpdateDTO.getEventGroupId()));
		}

		if(!isSameEventState(eventUpdateDTO, toUpdate)) {
			toUpdate.setEventState(findEventStateEntityById(eventUpdateDTO.getEventStateId()));
		}

		final EventEntity saved = eventRepository.save(toUpdate);

		return modelMapper.map(saved, EventDTO.class);
	}


	@Override
	public EventGroupDTO updateEventGroup(	final Integer id,
											final EventGroupUpdateDTO eventGroupUpdateDTO)
			throws EventGroupEntityNotFoundException {

		final EventGroupEntity toUpdate = findEventGroupEntityById(id);
		modelMapper.map(eventGroupUpdateDTO, toUpdate);

		final EventGroupEntity saved = eventGroupRepository.save(toUpdate);

		return modelMapper.map(saved, EventGroupDTO.class);
	}


	@Override
	public void deleteEventById(final Integer id) throws EventEntityNotFoundException {

		eventRepository.delete(findEventEntityById(id));
	}


	@Override
	public void deleteEventGroupById(final Integer id) throws EventGroupEntityNotFoundException {

		eventGroupRepository.delete(findEventGroupEntityById(id));
	}


	@Override
	public List<EventStateDTO> getEventStates() {

		final Iterable<EventStateEntity> eventStateEntities = eventStateRepository.findAll();

		return StreamSupport.stream(eventStateEntities.spliterator(), true)
				.map(eventStateEntity -> modelMapper.map(eventStateEntity, EventStateDTO.class))
				.collect(Collectors.toList());
	}


	private EventEntity findEventEntityById(final Integer id) throws EventEntityNotFoundException {

		final Optional<EventEntity> eventEntity = eventRepository.findById(id);

		if(!eventEntity.isPresent()) {
			throw new EventEntityNotFoundException();
		}

		return eventEntity.get();
	}


	private EventGroupEntity findEventGroupEntityById(final Integer id)
			throws EventGroupEntityNotFoundException {

		final Optional<EventGroupEntity> eventGroupEntity = eventGroupRepository.findById(id);

		if(!eventGroupEntity.isPresent()) {
			throw new EventGroupEntityNotFoundException();
		}

		return eventGroupEntity.get();
	}


	private EventStateEntity findEventStateEntityById(final Integer id)
			throws EventStateEntityNotFoundException {

		final Optional<EventStateEntity> eventSteateEntity = eventStateRepository.findById(id);

		if(!eventSteateEntity.isPresent()) {
			throw new EventStateEntityNotFoundException();
		}

		return eventSteateEntity.get();
	}


	private EventStateEntity findEventStateEntityByName(final String name)
			throws EventStateEntityNotFoundException {

		final Optional<EventStateEntity> eventSteateEntity = eventStateRepository.findByName(name);

		if(!eventSteateEntity.isPresent()) {
			throw new EventStateEntityNotFoundException();
		}

		return eventSteateEntity.get();
	}


	private boolean isSameEventState(	final EventUpdateDTO eventUpdateDTO,
										final EventEntity eventEntity) {

		if(eventEntity.getEventState() == null) {
			return false;
		}

		return eventEntity.getEventState().getId().equals(eventUpdateDTO.getEventStateId());
	}


	private boolean isSameEventGroup(	final EventUpdateDTO eventUpdateDTO,
										final EventEntity eventEntity) {

		if(eventEntity.getEventGroup() == null) {
			return false;
		}

		return eventEntity.getEventGroup().getId().equals(eventUpdateDTO.getEventGroupId());
	}
}
