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


	@GetMapping("/event/{id}")
	@ApiOperation(value = "Get a event group.", response = EventDTO.class)
	public ResponseEntity<EventDTO> getEventById(
			final @PathVariable Integer id) {

		try {
			return ResponseEntity.ok().body(eventService.getEventById(id));

		} catch(final EventEntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}


	@GetMapping("/event/all")
	@ApiOperation(
			value = "Get a list of all events within a event group.",
			response = EventDTO.class,
			responseContainer = "List")
	public ResponseEntity<List<EventDTO>> getAllEventsByEventGroupId(
			final @RequestParam("EventGroupId") Integer id) {

		try {
			return ResponseEntity.ok().body(eventService.getAllEventsByEventGroupId(id));

		} catch(final EventGroupEntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}


	@GetMapping("/event/mine")
	@ApiOperation(
			value = "Get all the event of the current authentificated user.",
			response = EventDTO.class,
			responseContainer = "List")
	public ResponseEntity<List<EventDTO>> getAllEventsByCurrentUser() {

		return ResponseEntity.ok().body(eventService.getAllEventsByCurrentUser());
	}


	@GetMapping("/eventGroup/{id}")
	@ApiOperation(value = "Get a event group.", response = EventGroupDTO.class)
	public ResponseEntity<EventGroupDTO> getEventGroupById(
			final @PathVariable Integer id) {

		try {
			return ResponseEntity.ok().body(eventService.getEventGroupById(id));

		} catch(final EventGroupEntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}

	}


	@GetMapping("/eventGroup/all")
	@ApiOperation(
			value = "Get a list of all event groups.",
			response = EventGroupDTO.class,
			responseContainer = "List")
	public ResponseEntity<List<EventGroupDTO>> getAllEventsGroups() {

		return ResponseEntity.ok().body(eventService.getAllEventGroups());
	}


	@GetMapping("/eventState/all")
	@ApiOperation(
			value = "Get a list of all event states.",
			response = EventStateDTO.class,
			responseContainer = "List")
	public ResponseEntity<List<EventStateDTO>> getAllEventsStates() {

		return ResponseEntity.ok().body(eventService.getEventStates());
	}


	@PostMapping("/event")
	@ApiOperation(
			value = "Add a event to a group event.",
			response = EventCreateDTO.class)
	public ResponseEntity<EventDTO> createEvent(
			final @Validated @RequestBody EventCreateDTO eventCreateDTO) {

		try {
			return ResponseEntity.ok().body(eventService.createEvent(eventCreateDTO));

		} catch(final EventGroupEntityNotFoundException | EventStateEntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}

	}


	@PostMapping("/eventGroup")
	@ApiOperation(
			value = "Add a event group.",
			response = EventGroupCreateDTO.class)
	public ResponseEntity<EventGroupDTO> createEventGroup(
			final @Validated @RequestBody EventGroupCreateDTO dto) {

		return ResponseEntity.ok().body(eventService.createEventGroup(dto));
	}


	@PutMapping("/event/{id}")
	@ApiOperation(value = "Update a event.", response = EventUpdateDTO.class)
	public ResponseEntity<EventDTO> updateEvent(
			final @PathVariable Integer id,
			final @Validated @RequestBody EventUpdateDTO dto) {

		try {
			return ResponseEntity.ok().body(eventService.updateEvent(id, dto));

		} catch(final EventEntityNotFoundException
				| EventGroupEntityNotFoundException
				| EventStateEntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}


	@PutMapping("/eventGroup/{id}")
	@ApiOperation(
			value = "Update a event group.",
			response = EventGroupUpdateDTO.class)
	public ResponseEntity<EventGroupDTO> updateEventGroup(
			final @PathVariable Integer id,
			final @Validated @RequestBody EventGroupUpdateDTO dto) {

		try {
			return ResponseEntity.ok().body(eventService.updateEventGroup(id, dto));

		} catch(final EventGroupEntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}


	@DeleteMapping("/event/{id}")
	@ApiOperation(value = "Delete a event.")
	public ResponseEntity<?> deleteEvent(final @PathVariable Integer id) {

		try {
			eventService.deleteEventById(id);

			return ResponseEntity.ok().build();

		} catch(final EventEntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}


	@DeleteMapping("/eventGroup/{id}")
	@ApiOperation(value = "Delete a event group.")
	public ResponseEntity<?> deleteEventGroup(final @PathVariable Integer id) {

		try {
			eventService.deleteEventGroupById(id);

			return ResponseEntity.ok().build();

		} catch(final EventGroupEntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
