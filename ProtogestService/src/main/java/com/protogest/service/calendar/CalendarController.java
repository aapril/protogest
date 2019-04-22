package com.protogest.service.calendar;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class CalendarController {

    @Autowired
    private CalendarService calendarService;


    @RequestMapping(value = "/calendar/recommendation", method = GET)
    public ResponseEntity<DatesRecommendation> getRecommendations(@RequestParam(value = "dates", required = false) Optional<List<String>> dates) {
        List<DateTime> occupiedDates = dates.isPresent()
                ? dates.get().stream().map(date -> new DateTime(date)).collect(Collectors.toList())
                : emptyList();
        return ResponseEntity.ok().body(calendarService.getDatesForCourSuperieureDuQuebec(occupiedDates));
    }
}
