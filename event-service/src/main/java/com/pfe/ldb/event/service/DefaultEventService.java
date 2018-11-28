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

		if (!eventRepository.existsById(id)) {
			throw new EventEntityNotFoundException();
		}

		final EventEntity eventEntity = eventRepository.findById(id).get();

		return modelMapper.map(eventEntity, EventDTO.class);
	}

	@Override
	public EventGroupDTO getEventGroupById(final Integer id) throws EventGroupEntityNotFoundException {

		if (!eventGroupRepository.existsById(id)) {
			throw new EventGroupEntityNotFoundException();
		}

		final EventGroupEntity eventGroupEntity = eventGroupRepository.findById(id).get();

		return modelMapper.map(eventGroupEntity, EventGroupDTO.class);
	}

	@Override
	public List<EventGroupDTO> getEventGroups() {

		final Iterable<EventGroupEntity> eventGroupEntities = eventGroupRepository.findAll();

		return StreamSupport.stream(eventGroupEntities.spliterator(), true)
				.map(eventGroupEntity -> modelMapper.map(eventGroupEntity, EventGroupDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<EventDTO> getEventsByEventGroupId(final Integer eventGroupId) throws EventGroupEntityNotFoundException {

		if (!eventGroupRepository.existsById(eventGroupId)) {
			throw new EventGroupEntityNotFoundException();
		}

		final Iterable<EventEntity> eventEntities = eventRepository.findByEventGroupId(eventGroupId);

		return StreamSupport.stream(eventEntities.spliterator(), true)
				.map(eventEntity -> modelMapper.map(eventEntity, EventDTO.class)).collect(Collectors.toList());
	}

	@Override
	public EventDTO createEvent(final EventCreateDTO eventCreateDTO)
			throws EventGroupEntityNotFoundException, EventStateEntityNotFoundException {

		final Optional<EventGroupEntity> eventGroupEntity = eventGroupRepository
				.findById(eventCreateDTO.getEventGroupId());

		if (!eventGroupEntity.isPresent()) {
			throw new EventGroupEntityNotFoundException();
		}

		final Optional<EventStateEntity> eventStateEntity = eventStateRepository
				.findByName(EVENT_STATE_PENDING);

		if (!eventStateEntity.isPresent()) {
			throw new EventStateEntityNotFoundException();
		}

		final EventEntity toCreate = modelMapper.map(eventCreateDTO, EventEntity.class);
		toCreate.setEventGroup(eventGroupEntity.get());
		toCreate.setEventState(eventStateEntity.get());
		final EventEntity saved = eventRepository.save(toCreate);

		return modelMapper.map(saved, EventDTO.class);
	}

	@Override
	public EventGroupDTO createEventGroup(final EventGroupCreateDTO eventGroupCreateDTO) {

		EventGroupEntity toSave = modelMapper.map(eventGroupCreateDTO, EventGroupEntity.class);
		final EventGroupEntity saved = eventGroupRepository.save(toSave);

		return modelMapper.map(saved, EventGroupDTO.class);
	}

	@Override
	public EventDTO updateEvent(final EventUpdateDTO eventUpdateDTO) throws EventEntityNotFoundException {

		final Optional<EventEntity> eventEntity = eventRepository.findById(eventUpdateDTO.getId());

		if (!eventEntity.isPresent()) {
			throw new EventEntityNotFoundException();
		}

		final EventEntity toUpdate = eventEntity.get();
		modelMapper.map(eventUpdateDTO, toUpdate);
		final EventEntity saved = eventRepository.save(toUpdate);

		return modelMapper.map(saved, EventDTO.class);
	}

	@Override
	public EventGroupDTO updateEventGroup(final EventGroupUpdateDTO eventGroupUpdateDTO)
			throws EventGroupEntityNotFoundException {

		final Optional<EventGroupEntity> eventGroupEntity = eventGroupRepository.findById(eventGroupUpdateDTO.getId());

		if (!eventGroupEntity.isPresent()) {
			throw new EventGroupEntityNotFoundException();
		}

		final EventGroupEntity toUpdate = eventGroupEntity.get();
		modelMapper.map(eventGroupUpdateDTO, toUpdate);
		final EventGroupEntity saved = eventGroupRepository.save(toUpdate);

		return modelMapper.map(saved, EventGroupDTO.class);
	}

	@Override
	public void deleteEventById(final Integer id) throws EventEntityNotFoundException {

		if (!eventRepository.existsById(id)) {
			throw new EventEntityNotFoundException();
		}

		eventRepository.deleteById(id);
	}

	@Override
	public void deleteEventGroupById(final Integer id) throws EventGroupEntityNotFoundException {

		if (!eventGroupRepository.existsById(id)) {
			throw new EventGroupEntityNotFoundException();
		}

		eventGroupRepository.deleteById(id);
	}

	@Override
	public List<EventStateDTO> getEventStates() {

		final Iterable<EventStateEntity> eventStateEntities = eventStateRepository.findAll();

		return StreamSupport.stream(eventStateEntities.spliterator(), true)
				.map(eventStateEntity -> modelMapper.map(eventStateEntity, EventStateDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<EventDTO> getEventsByCurrentUser() {

		// TODO : Change logic to fetch event of the current user.

		final Iterable<EventEntity> eventEntities = eventRepository.findAll();

		return StreamSupport.stream(eventEntities.spliterator(), true)
				.map(eventEntity -> modelMapper.map(eventEntity, EventDTO.class)).collect(Collectors.toList());
	}
}
