package com.pfe.ldb.event.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
import com.pfe.ldb.event.service.EventService;

import io.swagger.annotations.ApiOperation;

@RestController
public class EventController {

	private @Autowired EventService eventService;

	@GetMapping("/eventGroup/all")
	@ApiOperation(value = "Get a list of all event groups.", response = EventGroupDTO.class, responseContainer = "List")
	public ResponseEntity<List<EventGroupDTO>> getAllEventsGroups() {

		final List<EventGroupDTO> responseBody = eventService.getEventGroups();

		return ResponseEntity.ok().body(responseBody);
	}

	@GetMapping("/event/all")
	@ApiOperation(value = "Get a list of all events within a event group.", response = EventDTO.class, responseContainer = "List")
	public ResponseEntity<List<EventDTO>> getAllEventsByEventGroupId(final @RequestParam Integer eventGroupId) {

		try {
			List<EventDTO> responseBody = eventService.getEventsByEventGroupId(eventGroupId);

			return ResponseEntity.ok().body(responseBody);

		} catch (final EventGroupEntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/event/mine")
	@ApiOperation(value = "Get all the event of the current authentificated user.", response = EventDTO.class, responseContainer = "List")
	public ResponseEntity<List<EventDTO>> getAllEventsByCurrentUser() {

		final List<EventDTO> responseBody = eventService.getEventsByCurrentUser();

		return ResponseEntity.ok().body(responseBody);
	}

	@GetMapping("/eventState/all")
	@ApiOperation(value = "Get a list of all event states.", response = EventStateDTO.class, responseContainer = "List")
	public ResponseEntity<List<EventStateDTO>> getAllEventsStates() {

		final List<EventStateDTO> responseBody = eventService.getEventStates();

		return ResponseEntity.ok().body(responseBody);
	}

	@GetMapping("/event/{id}")
	@ApiOperation(value = "Get a event group.", response = EventDTO.class)
	public ResponseEntity<EventDTO> getEventById(final @PathVariable Integer id) {

		try {
			final EventDTO responseBody = eventService.getEventById(id);

			return ResponseEntity.ok().body(responseBody);

		} catch (final EventEntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/eventGroup/{id}")
	@ApiOperation(value = "Get a event group.", response = EventGroupDTO.class)
	public ResponseEntity<EventGroupDTO> getEventGroupById(final @PathVariable Integer id) {

		try {
			final EventGroupDTO responseBody = eventService.getEventGroupById(id);

			return ResponseEntity.ok().body(responseBody);

		} catch (final EventGroupEntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}

	}

	@PostMapping("/event")
	@ApiOperation(value = "Add a event to a group event.", response = EventCreateDTO.class)
	public ResponseEntity<EventDTO> createEvent(final @Validated @RequestBody EventCreateDTO eventCreateDTO) {

		EventDTO responseBody;
		try {
			responseBody = eventService.createEvent(eventCreateDTO);
			
		} catch (final EventGroupEntityNotFoundException | EventStateEntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().body(responseBody);
	}

	@PostMapping("/eventGroup")
	@ApiOperation(value = "Add a event group.", response = EventGroupCreateDTO.class)
	public ResponseEntity<EventGroupDTO> createEventGroup(
			final @Validated @RequestBody EventGroupCreateDTO eventGroupCreateDTO) {

		final EventGroupDTO responseBody = eventService.createEventGroup(eventGroupCreateDTO);

		return ResponseEntity.ok().body(responseBody);
	}

	@PutMapping("/event/{id}")
	@ApiOperation(value = "Update a event.", response = EventUpdateDTO.class)
	public ResponseEntity<EventDTO> updateEvent(final @Validated @RequestBody EventUpdateDTO eventUpdateDTO) {

		try {
			final EventDTO responseBody = eventService.updateEvent(eventUpdateDTO);

			return ResponseEntity.ok().body(responseBody);

		} catch (final EventEntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/eventGroup/{id}")
	@ApiOperation(value = "Update a event group.", response = EventGroupUpdateDTO.class)
	public ResponseEntity<EventGroupDTO> updateEventGroup(
			final @Validated @RequestBody EventGroupUpdateDTO eventGroupUpdateDTO) {

		try {
			final EventGroupDTO responseBody = eventService.updateEventGroup(eventGroupUpdateDTO);

			return ResponseEntity.ok().body(responseBody);

		} catch (final EventGroupEntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/event/{id}")
	@ApiOperation(value = "Delete a event.")
	public ResponseEntity<?> deleteEvent(final @PathVariable Integer id) {

		try {
			eventService.deleteEventById(id);

			return ResponseEntity.ok().build();

		} catch (final EventEntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/eventGroup/{id}")
	@ApiOperation(value = "Delete a event group.")
	public ResponseEntity<?> deleteEventGroup(final @PathVariable Integer id) {

		try {
			eventService.deleteEventGroupById(id);

			return ResponseEntity.ok().build();

		} catch (final EventGroupEntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
