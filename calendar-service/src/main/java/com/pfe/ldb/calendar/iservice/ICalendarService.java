package com.pfe.ldb.calendar.iservice;

import java.util.Date;
import java.util.List;

import com.google.api.services.calendar.model.Event;

public interface ICalendarService {

	void saveEvents(List<Event> events);
	List<com.pfe.ldb.core.protogest.event.Event> generateDate(Date date);
}
