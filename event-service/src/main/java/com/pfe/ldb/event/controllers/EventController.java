package com.pfe.ldb.event.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.ldb.core.protogest.event.Event;
import com.pfe.ldb.core.protogest.event.EventGroup;
import com.pfe.ldb.core.protogest.event.EventJson;
import com.pfe.ldb.entities.SuggestionEntity;
import com.pfe.ldb.event.services.EventService;

@RestController
public class EventController {

	
	private @Autowired EventService eventService;

	
	@RequestMapping("/events")
	public List<EventJson> getEvents() throws Exception {
		
		return eventService.loadEvents();
	}
	
	
	@RequestMapping("/events/user/")
	public List<EventJson> getEventsForCurrentUser(final @RequestHeader String email) throws Exception {
		return eventService.loadEventsForCurrentUser(email);
	}

	
	@RequestMapping("/events/group")
	public List<EventGroup> getEventsGroup() throws Exception {
		return eventService.loadEventsGroup();
	}
	
	
	@RequestMapping("/event/user/suggestion")
	public List<SuggestionEntity> getSuggestionForCurrentUser(final @RequestHeader String email, 
															  final @RequestHeader String eventId) 
					throws Exception {
		
		return eventService.loadSuggestionForCurrentUser(email, eventId);
	}

	
	@RequestMapping("/event")
	public Event updateEvent(@RequestBody(required = true) final Map<String, String> event) throws Exception {
		
		return eventService.updateEventForCurrentUser(event);
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/event/user/suggestion")
	public Boolean updateEventWithSuggestion(@RequestBody(required = true) final Map<String, String> event) throws Exception {
		
		return eventService.updateEventWithSuggestionForCurrentUser(event);
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/events")
	public List<Event> updateEvents(@RequestBody(required = true) final Map<String, String> events) throws Exception {
		List<Event> eventss = new ArrayList<>();
		Thread.sleep(1000);
		int cnt = 1;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String eventName = events.get("eventName");
		List<String> emailsToNotify = new ArrayList<>();
		emailsToNotify.add(events.get("email1"));
		emailsToNotify.add(events.get("email2"));

		for (String key : events.keySet()) {
			if(!key.equals("eventName") && !key.equals("email1") && !key.equals("email2")) {
				eventss.add(new Event(formatter.parse(events.get(key)), cnt++, eventName,emailsToNotify));
			}
		}
		return eventService.updateEvents(eventss);
	}

	

}
