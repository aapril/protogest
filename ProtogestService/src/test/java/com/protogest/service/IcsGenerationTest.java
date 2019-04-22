package com.protogest.service;

import com.protogest.model.form.EFieldType;
import com.protogest.model.form.nosql.ProtocoleInstance;
import com.protogest.repository.ProtocoleInstanceRepository;
import com.protogest.service.calendar.CalendarService;
import mockit.Mock;
import mockit.MockUp;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.component.VEvent;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.protogest.service.Constants.CourSuperieurFieldDescriptions;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class IcsGenerationTest {

    private CalendarService calendarService;

    @Test
    public void createsCalendarWithValidEvents() {
        calendarService = new CalendarService();

        ProtocoleInstance protocole = new ProtocoleInstance();
        protocole.setFields(asList(new ProtocoleInstance.FormField("999", "2019-01-01", EFieldType.DATE)));
        Calendar calendar = calendarService.createCalendarFromProtocol(protocole);
        VEvent event = (VEvent) calendar.getComponents().get(0);
        assertThat(event.getProperty("summary").getValue(), is("Événement de protocole d'instance"));
        assertThat(event.getProperty("dtstart").getValue(), is("20190101"));

    }

    @Test
    public void createsCalendarWithCorrectNumberOfEvents() {
        calendarService = new CalendarService();

        ProtocoleInstance protocole = new ProtocoleInstance();
        protocole.setFields(asList(new ProtocoleInstance.FormField("1", "2019-01-01", EFieldType.DATE),
                new ProtocoleInstance.FormField("1", "2019-01-01", EFieldType.DATE)));
        Calendar calendar = calendarService.createCalendarFromProtocol(protocole);
        assertThat(calendar.getComponents().size(), is(2));

    }

    @Test
    public void createsIcsFile() {
        calendarService = new CalendarService();
        ProtocoleInstance protocole = new ProtocoleInstance();
        protocole.setFields(asList(new ProtocoleInstance.FormField("1", "2019-01-01", EFieldType.DATE)));

        new MockUp<ProtocoleInstanceRepository>() {
            @Mock
            public ProtocoleInstance get(String value) {
                return protocole;
            }
        };


        File ics = calendarService.createIcsFromProtocol("1");
        assertThat(ics, notNullValue());
    }
}
