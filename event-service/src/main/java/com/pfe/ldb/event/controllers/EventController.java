package com.pfe.ldb.event.controllers;

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

import com.pfe.ldb.event.models.EventDTO;
import com.pfe.ldb.event.models.EventGroupDTO;
import com.pfe.ldb.event.models.EventStateDTO;
import com.pfe.ldb.event.repositories.exceptions.EventEntityNotDeletedException;
import com.pfe.ldb.event.repositories.exceptions.EventEntityNotFoundException;
import com.pfe.ldb.event.repositories.exceptions.EventGroupEntityNotFoundException;
import com.pfe.ldb.event.services.EventService;

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
    public ResponseEntity<List<EventDTO>> getAllEventsByEventGroupId(
    		final @RequestParam Integer eventGroupId) {
		
		try {
			List<EventDTO> responseBody = eventService.getEventsByEventGroupId(eventGroupId);
			
			return ResponseEntity.ok().body(responseBody); 
			
		} catch (EventGroupEntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
    }
	
	
	@GetMapping("/event/mine")
	@ApiOperation(value = "Get all the event of the current authentificated user.", response = EventDTO.class, responseContainer = "List")
    public ResponseEntity<List<EventDTO>> getAllEventsByCurrentUser() {
		
		List<EventDTO> responseBody = eventService.getEventsByCurrentUser();
		
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
			final EventDTO responseBody= eventService.getEventById(id);
			
			return ResponseEntity.ok().body(responseBody);
			
		} catch (EventEntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
    }
	
	
	@GetMapping("/eventGroup/{id}")
	@ApiOperation(value = "Get a event group.", response = EventGroupDTO.class)
    public ResponseEntity<EventGroupDTO> getEventGroupById(final @PathVariable Integer id) {
		
		try {
			final EventGroupDTO responseBody = eventService.getEventGroupById(id);

			return ResponseEntity.ok().body(responseBody);
		
		} catch (EventGroupEntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
		
    }
	
	
	@PostMapping("/event")
	@ApiOperation(value = "Add a event to a group event.", response = EventDTO.class)
	public ResponseEntity<EventDTO> createEvent(
			final @Validated @RequestBody EventDTO eventDTO) {
		
		final EventDTO responseBody = eventService.createEvent(eventDTO);
		
		return ResponseEntity.ok().body(responseBody);
	}
	
	
	@PostMapping("/eventGroup")
	@ApiOperation(value = "Add a event group.", response = EventGroupDTO.class)
	public ResponseEntity<EventGroupDTO> createEventGroup(
			final @Validated @RequestBody EventGroupDTO eventGroupDTO) {
		
		final EventGroupDTO responseBody = eventService.createEventGroup(eventGroupDTO);
		
		return ResponseEntity.ok().body(responseBody);
	}
	
	
	@PutMapping("/event/{id}")
	@ApiOperation(value = "Update a event.", response = EventDTO.class)
	public ResponseEntity<EventDTO> updateEvent(
			final @PathVariable Integer id, 
			final @RequestBody EventDTO eventDTO) {
		
		try {
			final EventDTO responseBody = eventService.updateEvent(id, eventDTO);
			
			return ResponseEntity.ok().body(responseBody);
			
		} catch (EventEntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}	
	}
	
	
	@PutMapping("/eventGroup/{id}")
	@ApiOperation(value = "Update a event group.", response = EventGroupDTO.class)
	public ResponseEntity<EventGroupDTO> updateEventGroup(
			final @PathVariable Integer id, 
			final @RequestBody EventGroupDTO eventGroupDTO) {
		
		try {
			final EventGroupDTO responseBody = eventService.updateEventGroup(id, eventGroupDTO);
			
			return ResponseEntity.ok().body(responseBody);
			
		} catch (EventGroupEntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	@DeleteMapping("/event/{id}")
	@ApiOperation(value = "Delete a event.")
	public ResponseEntity<?> deleteEvent(final @PathVariable Integer id) {
			
		try {
			eventService.deleteEventById(id);
			
			return ResponseEntity.ok().build();
			
		} catch (EventEntityNotFoundException e) {
			
			return ResponseEntity.notFound().build();
		}
	}
	
	
	@DeleteMapping("/eventGroup/{id}")
	@ApiOperation(value = "Delete a event group.")
	public ResponseEntity<?> deleteEventGroup(final @PathVariable Integer id) {
			
		try {
			eventService.deleteEventGroupById(id);
			
			return ResponseEntity.ok().build();
			
		} catch (EventGroupEntityNotFoundException e) {
			
			return ResponseEntity.notFound().build();
		}
	}
}
