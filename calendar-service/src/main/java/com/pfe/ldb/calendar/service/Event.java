package com.pfe.ldb.calendar.service;

import lombok.NonNull;

import java.util.Date;

public class Event {

    private @NonNull
    String name;

    private @NonNull String description;

    private @NonNull
    Date eventDate;

    private @NonNull Integer eventGroupId;

    private @NonNull Integer memberId;

    private @NonNull Integer eventStateId;
}
