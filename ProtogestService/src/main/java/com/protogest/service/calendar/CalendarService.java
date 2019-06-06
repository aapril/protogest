package com.protogest.service.calendar;

import com.protogest.model.form.EFieldType;
import com.protogest.model.form.nosql.ProtocoleInstance;
import com.protogest.repository.ProtocoleInstanceRepository;
import com.protogest.service.Constants.CourSuperieurDuQuebecDateFields;
import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Version;
import net.fortuna.ical4j.util.UidGenerator;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.net.SocketException;
import java.util.List;

import static com.protogest.service.Constants.CourSuperieurFieldDescriptions;

@Service
public class CalendarService {


    public DatesRecommendation getDatesForCourSuperieureDuQuebec(List<DateTime> occupiedDates) {
        DatesRecommendation dates = new DatesRecommendation();
        DateTime currentDate = new DateTime();
        for (int i = 0; i < CourSuperieurDuQuebecDateFields.values().length; i++) {
            if (currentDate.getDayOfWeek() == DateTimeConstants.TUESDAY) {
                currentDate = currentDate.plusDays(3);
            } else if (currentDate.getDayOfWeek() == DateTimeConstants.WEDNESDAY) {
                currentDate = currentDate.plusDays(5);
            } else currentDate = currentDate.plusDays(4);

            DateTime comparableCurrentDate = currentDate;
            if (occupiedDates.stream().anyMatch(d -> d.withTimeAtStartOfDay().equals(comparableCurrentDate.withTimeAtStartOfDay())))
                currentDate = currentDate.plusDays(1);
            dates.addDate(new DateField(CourSuperieurDuQuebecDateFields.values()[i], currentDate));
        }
        return dates;
    }

    public Calendar createCalendarFromProtocol(ProtocoleInstance protocole) {
        Calendar calendar = new Calendar();
        calendar.getProperties().add(new ProdId("Protogest"));
        calendar.getProperties().add(Version.VERSION_2_0);
        calendar.getProperties().add(CalScale.GREGORIAN);

        UidGenerator ug = null;
        try {
            ug = new UidGenerator("1");
        } catch (SocketException e) {
        }

        UidGenerator finalUg = ug;
        protocole.getFields().stream().filter(f -> f.getType().equals(EFieldType.DATE)).forEach(f -> {
            VEvent a = new VEvent(new Date(new DateTime(f.getValue()).toDate()), CourSuperieurFieldDescriptions.getOrDefault(f.getId(), "Événement de protocole d'instance"));
            a.getProperties().add(finalUg.generateUid());
            calendar.getComponents().add(a);

        });


        return calendar;
    }

    public File createIcsFromProtocol(String protoId) {
        ProtocoleInstance protocole = ProtocoleInstanceRepository.get(protoId);
        Calendar calendar = createCalendarFromProtocol(protocole);
        try {
            File file = new File("calendar.ics");
            Writer writer;
            writer = new FileWriter(file);
            CalendarOutputter outputter = new CalendarOutputter();
            outputter.output(calendar, writer);
            writer.close();
        } catch (Exception e) {
        }
        return new File("calendar.ics");
    }
}
