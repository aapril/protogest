package com.pfe.ldb.event.services;

import java.util.List;
import java.util.Map;

import com.pfe.ldb.core.protogest.event.Event;
import com.pfe.ldb.core.protogest.event.EventGroup;
import com.pfe.ldb.core.protogest.event.EventJson;
import com.pfe.ldb.entities.SuggestionEntity;


public interface EventService {

	public List<EventJson> loadEvents();
	public List<EventGroup> loadEventsGroup();
	public List<EventJson> loadEventsForCurrentUser(String user);
	public List<SuggestionEntity> loadSuggestionForCurrentUser(String user, String eventId);
	public Event updateEvent(Event event);
	public Event updateEventForCurrentUser(Map<String,String> event);
	public Boolean updateEventWithSuggestionForCurrentUser(Map<String,String> event);
	public List<Event> updateEvents(List<Event> event);




}
