package com.pfe.ldb.event.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfe.ldb.event.dao.exception.EventEntityNotFoundException;
import com.pfe.ldb.event.dao.exception.EventGroupEntityNotFoundException;
import com.pfe.ldb.event.dao.exception.EventStateEntityNotFoundException;
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

	public EventDTO getEventById(final Integer id)
		throws EventEntityNotFoundException;


	public List<EventDTO> getAllEventsByEventGroupId(final Integer id)
		throws EventGroupEntityNotFoundException;


	public List<EventDTO> getAllEventsByCurrentUser();


	public List<EventGroupDTO> getAllEventGroups();


	public EventGroupDTO getEventGroupById(final Integer id)
		throws EventGroupEntityNotFoundException;


	public List<EventStateDTO> getEventStates();


	public EventDTO createEvent(final EventCreateDTO dto)
		throws EventGroupEntityNotFoundException,
		EventStateEntityNotFoundException;


	public EventGroupDTO createEventGroup(final EventGroupCreateDTO dto);


	public EventDTO updateEvent(final Integer id, final EventUpdateDTO dto)
		throws EventEntityNotFoundException,
		EventGroupEntityNotFoundException,
		EventStateEntityNotFoundException;


	public EventGroupDTO updateEventGroup(final Integer id, final EventGroupUpdateDTO dto)
		throws EventGroupEntityNotFoundException;


	public void deleteEventById(final Integer id)
		throws EventEntityNotFoundException;


	public void deleteEventGroupById(final Integer id)
		throws EventGroupEntityNotFoundException;
}
