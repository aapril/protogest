package com.pfe.ldb.calendar.service;

import java.util.Date;
import java.util.List;

import com.google.api.services.calendar.model.Event;
import com.pfe.ldb.calendar.dao.entity.EventEntity;

public interface CalendarService {

	void saveEvents(final List<Event> events);
	
	List<EventEntity> generateDate(final Date date);
}
