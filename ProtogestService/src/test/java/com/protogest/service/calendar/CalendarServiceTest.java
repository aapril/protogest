package com.protogest.service.calendar;


import com.protogest.service.Constants.CourSuperieurDuQuebecDateFields;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

class CalendarServiceTest {

    private CalendarService calendarService;

    @Test
    public void returnListOfDatesRecommentationForSupremeQuebec() {
        calendarService = new CalendarService();
        DatesRecommendation dates = calendarService.getDatesForCourSuperieureDuQuebec(emptyList());
        assertThat(dates, is(notNullValue()));
    }

    @Test
    public void listForSupremeQuebecContainsAllRequiredFields() {
        calendarService = new CalendarService();
        DatesRecommendation dates = calendarService.getDatesForCourSuperieureDuQuebec(emptyList());
        Arrays.stream(CourSuperieurDuQuebecDateFields.values())
                .forEach(f ->
                        assertThat(dates.getDates().stream().filter(d -> d.getField().equals(f)).count(), is(1L)));
    }

    @Test
    public void returnsUniqueDates() {
        calendarService = new CalendarService();
        DatesRecommendation dates = calendarService.getDatesForCourSuperieureDuQuebec(emptyList());
        dates.getDates().forEach(d ->
                assertThat(dates.getDates().stream().filter(a -> a.getDate().equals(d.getDate())).count(), equalTo(1L)));
    }

    @Test
    public void returnsDateNotOnWeekends() {
        calendarService = new CalendarService();
        DatesRecommendation dates = calendarService.getDatesForCourSuperieureDuQuebec(emptyList());
        dates.getDates().forEach(d -> {
            DateTime date = new DateTime(d.getDate());
            assertThat(date.getDayOfWeek(), not(DateTimeConstants.SUNDAY));
            assertThat(date.getDayOfWeek(), not(DateTimeConstants.SATURDAY));
        });
    }

    @Test
    public void doesNotRecommendDatesAlreadyOccupied() {
        calendarService = new CalendarService();
        DateTime currentDate = new DateTime().plusDays(4);

        DatesRecommendation dates = calendarService.getDatesForCourSuperieureDuQuebec(asList(currentDate));
        dates.getDates().forEach(d -> assertThat(currentDate.withTimeAtStartOfDay().isEqual(new DateTime(d.getDate()).withTimeAtStartOfDay()), is(false)));
    }
}