package com.protogest.service.calendar;

import java.util.ArrayList;
import java.util.List;

public class DatesRecommendation {
    private List<DateField> dates = new ArrayList<>();

    public void addDate(DateField date) {
        dates.add(date);
    }

    public List<DateField> getDates() {
        return dates;
    }
}

