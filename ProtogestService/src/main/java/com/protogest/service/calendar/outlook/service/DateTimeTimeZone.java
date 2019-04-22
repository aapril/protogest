package com.protogest.service.calendar.outlook.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DateTimeTimeZone {
	private Date dateTime;
	
	public Date getDateTime() {
		return dateTime;
	}
}
