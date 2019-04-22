package com.protogest.service.calendar;

import com.protogest.service.Constants;
import com.protogest.service.Constants.CourSuperieurDuQuebecDateFields;
import org.joda.time.DateTime;

import java.util.Date;

public class DateField {
    private CourSuperieurDuQuebecDateFields field;
    private DateTime date;

    public DateField(CourSuperieurDuQuebecDateFields field, DateTime date) {

        this.field = field;
        this.date = date;
    }

    public CourSuperieurDuQuebecDateFields getField() {
        return field;
    }

    public Date getDate() {
        return date.toDate();
    }
}
