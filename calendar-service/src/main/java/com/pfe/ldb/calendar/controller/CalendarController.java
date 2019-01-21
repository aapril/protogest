package com.pfe.ldb.calendar.controller;



import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.pfe.ldb.calendar.service.Event;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.pfe.ldb.calendar.service.CalendarService;

@RestController
public class CalendarController {

	private final static Log logger = LogFactory.getLog(CalendarController.class);
	private static final String APPLICATION_NAME = "";
//	private static HttpTransport httpTransport;
//	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
//	private static com.google.api.services.calendar.Calendar client;

//	GoogleClientSecrets clientSecrets;
//	GoogleAuthorizationCodeFlow flow;
//	Credential credential;
	@Autowired
	private CalendarService calendarService;
	
	@Value("${google.client.client-id}")
	private String clientId;
	@Value("${google.client.client-secret}")
	private String clientSecret;
	private Set<Event> events = new HashSet<>();

	final LocalDateTime date1 = LocalDateTime.parse("2018-07-01T16:30:00.000+05:30");
	final LocalDateTime date2 = LocalDateTime.now();
	
	

	public void setEvents(Set<Event> events) {
		this.events = events;
	}


	@CrossOrigin(origins="http://localhost:3001")
    @RequestMapping(value = "/calendar", method = RequestMethod.GET)
	public ResponseEntity<String> calendar(HttpServletRequest request) {

//		com.google.api.services.calendar.model.Events eventList;
//		String message;
//
//		try {
//			String authorization = request.getHeader("Authorization");
//			String[] token = authorization.split(" ");
//			if (flow == null) {
//				Details web = new Details();
//				web.setClientId(clientId);
//				web.setClientSecret(clientSecret);
//				clientSecrets = new GoogleClientSecrets().setWeb(web);
//				httpTransport = GoogleNetHttpTransport.newTrustedTransport();
//				flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY, clientSecrets,
//						Collections.singleton(CalendarScopes.CALENDAR)).build();
//			}
//			TokenResponse response = new TokenResponse().setAccessToken(token[1]).setTokenType(token[0]);
//			credential = flow.createAndStoreCredential(response, "userID");
//			client = new com.google.api.services.calendar.Calendar.Builder(httpTransport, JSON_FACTORY, credential)
//					.setApplicationName(APPLICATION_NAME).build();
//			Events events = client.events();
//			eventList = events.list("primary").setTimeMin(date1).setTimeMax(date2).execute();
//			calendarService.saveEvents(eventList.getItems());
//
//			message = eventList.getItems().toString();
//			System.out.println("My:" + eventList.getItems());
//		} catch (Exception e) {
//			logger.warn("Exception while handling OAuth2 callback (" + e.getMessage() + ")."
//					+ " Redirecting to google connection status page.");
//			message = "Exception while handling OAuth2 callback (" + e.getMessage() + ")."
//					+ " Redirecting to google connection status page.";
//		}
//
//		System.out.println("cal message:" + message);
//		return new ResponseEntity<>(message, HttpStatus.OK);
		return new ResponseEntity<>("not implemented", HttpStatus.OK);
	}
	

}