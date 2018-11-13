package com.pfe.ldb.event.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfe.ldb.event.models.EventDTO;
import com.pfe.ldb.event.models.EventGroupDTO;
import com.pfe.ldb.event.models.EventStateDTO;
import com.pfe.ldb.event.repositories.exceptions.EventEntityNotFoundException;
import com.pfe.ldb.event.repositories.exceptions.EventGroupEntityNotFoundException;

@Service
@Transactional(readOnly = true)
public interface EventService {

	public EventDTO getEventById(final Integer id) throws EventEntityNotFoundException;

	public EventGroupDTO getEventGroupById(final Integer id) throws EventGroupEntityNotFoundException;
	
	public List<EventDTO> getEventsByEventGroupId(final Integer eventGroupId) throws EventGroupEntityNotFoundException;
	
	public List<EventGroupDTO> getEventGroups();
	
	public List<EventStateDTO> getEventStates();
	
	public EventDTO createEvent(final EventDTO eventDTO);
	
	public EventGroupDTO createEventGroup(final EventGroupDTO eventGroupDTO);

	public EventDTO updateEvent(final Integer id, final EventDTO eventDTO) throws EventEntityNotFoundException;

	public EventGroupDTO updateEventGroup(final Integer id, final EventGroupDTO eventGroupDTO) throws EventGroupEntityNotFoundException;

	public void deleteEventById(final Integer id) throws EventEntityNotFoundException;

	public void deleteEventGroupById(final Integer id) throws EventGroupEntityNotFoundException;

	public List<EventDTO> getEventsByCurrentUser();
}
