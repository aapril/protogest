package com.pfe.ldb.event.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfe.ldb.event.dao.exception.EventEntityNotFoundException;
import com.pfe.ldb.event.dao.exception.EventGroupEntityNotFoundException;
import com.pfe.ldb.event.dto.EventCreateDTO;
import com.pfe.ldb.event.dto.EventDTO;
import com.pfe.ldb.event.dto.EventGroupCreateDTO;
import com.pfe.ldb.event.dto.EventGroupDTO;
import com.pfe.ldb.event.dto.EventGroupUpdateDTO;
import com.pfe.ldb.event.dto.EventStateDTO;
import com.pfe.ldb.event.dto.EventUpdateDTO;

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
