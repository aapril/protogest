package com.protogest.service.calendar.outlook.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {
	private String id;
	private DateTimeTimeZone start;
	private DateTimeTimeZone end;
	
	public String getId() {
		return id;
	}
	public DateTimeTimeZone getStart() {
		return start;
	}
	public void setStart(DateTimeTimeZone start) {
		this.start = start;
	}
	public DateTimeTimeZone getEnd() {
		return end;
	}
	public void setEnd(DateTimeTimeZone end) {
		this.end = end;
	}
}
