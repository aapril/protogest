package com.pfe.ldb.event.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfe.ldb.event.models.EventCreateDTO;
import com.pfe.ldb.event.models.EventDTO;
import com.pfe.ldb.event.models.EventGroupCreateDTO;
import com.pfe.ldb.event.models.EventGroupDTO;
import com.pfe.ldb.event.models.EventGroupUpdateDTO;
import com.pfe.ldb.event.models.EventStateDTO;
import com.pfe.ldb.event.models.EventUpdateDTO;
import com.pfe.ldb.repositories.exceptions.EventEntityNotFoundException;
import com.pfe.ldb.repositories.exceptions.EventGroupEntityNotFoundException;

@Service
@Transactional(readOnly = true)
public interface EventService {

	public EventDTO getEventById(final Integer id) throws EventEntityNotFoundException;

	public EventGroupDTO getEventGroupById(final Integer id) throws EventGroupEntityNotFoundException;
	
	public List<EventDTO> getEventsByEventGroupId(final Integer eventGroupId) throws EventGroupEntityNotFoundException;
	
	public List<EventGroupDTO> getEventGroups();
	
	public List<EventStateDTO> getEventStates();
	
	public EventDTO createEvent(final EventCreateDTO eventCreateDTO);
	
	public EventGroupDTO createEventGroup(final EventGroupCreateDTO eventGroupCreateDTO);

	public EventDTO updateEvent(final EventUpdateDTO eventUpdateDTO) throws EventEntityNotFoundException;

	public EventGroupDTO updateEventGroup(final EventGroupUpdateDTO eventGroupUpdateDTO) throws EventGroupEntityNotFoundException;

	public void deleteEventById(final Integer id) throws EventEntityNotFoundException;

	public void deleteEventGroupById(final Integer id) throws EventGroupEntityNotFoundException;

	public List<EventDTO> getEventsByCurrentUser();
}
