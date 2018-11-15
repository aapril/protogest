package com.pfe.ldb.calendar.iservice;

import java.util.Date;
import java.util.List;

import com.google.api.services.calendar.model.Event;
import com.pfe.ldb.entities.EventEntity;

public interface ICalendarService {

	void saveEvents(List<Event> events);
	List<EventEntity> generateDate(Date date);
}
