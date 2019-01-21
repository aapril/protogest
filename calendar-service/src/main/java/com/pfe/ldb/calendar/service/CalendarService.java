package com.pfe.ldb.calendar.service;

import java.util.Date;
import java.util.List;

import model.EventEntity;

public interface CalendarService {

	void saveEvents(final List<Event> events);
	
	List<EventEntity> generateDate(final Date date);
}
