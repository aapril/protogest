package com.protogest.service.calendar.outlook.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Calendar;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Token {
	@JsonProperty("access_token")
	private String accessToken;
	private String error;
	@JsonProperty("error_description")
	private String errorDescription;

	public String getAccessToken() {
		return accessToken;
	}
	public void setError(String error) {
		this.error = error;
	}
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

}
