package com.pfe.ldb.event.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfe.ldb.entities.EventEntity;
import com.pfe.ldb.entities.EventGroupEntity;
import com.pfe.ldb.entities.EventStateEntity;
import com.pfe.ldb.event.models.EventCreateDTO;
import com.pfe.ldb.event.models.EventDTO;
import com.pfe.ldb.event.models.EventGroupCreateDTO;
import com.pfe.ldb.event.models.EventGroupDTO;
import com.pfe.ldb.event.models.EventGroupUpdateDTO;
import com.pfe.ldb.event.models.EventStateDTO;
import com.pfe.ldb.event.models.EventUpdateDTO;
import com.pfe.ldb.repositories.EventGroupRepository;
import com.pfe.ldb.repositories.EventRepository;
import com.pfe.ldb.repositories.EventStateRepository;
import com.pfe.ldb.repositories.exceptions.EventEntityNotFoundException;
import com.pfe.ldb.repositories.exceptions.EventGroupEntityNotFoundException;

@Transactional
@Service
public class DefaultEventService implements EventService {

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
	public EventDTO createEvent(final EventCreateDTO eventCreateDTO) {

		final EventEntity toSave = modelMapper.map(eventCreateDTO, EventEntity.class);
		toSave.setEventState(eventStateRepository.findById(1).get());
		final EventEntity saved = eventRepository.save(toSave);

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
