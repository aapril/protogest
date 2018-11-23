package com.pfe.ldb.event.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfe.ldb.entities.EventEntity;
import com.pfe.ldb.entities.EventGroupEntity;
import com.pfe.ldb.entities.EventStateEntity;
import com.pfe.ldb.event.models.EventDTO;
import com.pfe.ldb.event.models.EventGroupDTO;
import com.pfe.ldb.event.models.EventStateDTO;
import com.pfe.ldb.event.repositories.EventGroupRepository;
import com.pfe.ldb.event.repositories.EventRepository;
import com.pfe.ldb.event.repositories.EventStateRepository;
import com.pfe.ldb.event.repositories.exceptions.EventEntityNotFoundException;
import com.pfe.ldb.event.repositories.exceptions.EventGroupEntityNotFoundException;

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
	public EventDTO createEvent(final EventDTO eventDTO) {

		final EventEntity eventEntityToSave = modelMapper.map(eventDTO, EventEntity.class);
		final EventEntity eventEntity = eventRepository.save(eventEntityToSave);

		return modelMapper.map(eventEntity, EventDTO.class);
	}

	@Override
	public EventGroupDTO createEventGroup(final EventGroupDTO eventGroupDTO) {

		EventGroupEntity eventGroupEntityToSave = modelMapper.map(eventGroupDTO, EventGroupEntity.class);
		final EventGroupEntity eventGroupEntity = eventGroupRepository.save(eventGroupEntityToSave);

		return modelMapper.map(eventGroupEntity, EventGroupDTO.class);
	}

	@Override
	public EventDTO updateEvent(final Integer id, final EventDTO eventDTO) throws EventEntityNotFoundException {

		if (!eventRepository.existsById(id)) {
			throw new EventEntityNotFoundException();
		}

		final EventEntity eventEntityToUpdate = eventRepository.findById(id).get();
		modelMapper.map(eventDTO, eventEntityToUpdate);
		final EventEntity eventEntity = eventRepository.save(eventEntityToUpdate);

		return modelMapper.map(eventEntity, EventDTO.class);
	}

	@Override
	public EventGroupDTO updateEventGroup(final Integer id, final EventGroupDTO eventGroupDTO)
			throws EventGroupEntityNotFoundException {

		if (!eventGroupRepository.existsById(id)) {
			throw new EventGroupEntityNotFoundException();
		}

		final EventGroupEntity eventGroupEntityToUpdate = eventGroupRepository.findById(id).get();
		modelMapper.map(eventGroupDTO, eventGroupEntityToUpdate);
		final EventGroupEntity eventGroupEntity = eventGroupRepository.save(eventGroupEntityToUpdate);

		return modelMapper.map(eventGroupEntity, EventGroupDTO.class);
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

		// Integer userId = 12312;

		// final Iterable<EventEntity> eventEntities =
		// eventRepository.findAllByUserId(userId);

		final Iterable<EventEntity> eventEntities = eventRepository.findAll();

		return StreamSupport.stream(eventEntities.spliterator(), true)
				.map(eventEntity -> modelMapper.map(eventEntity, EventDTO.class)).collect(Collectors.toList());
	}
}
