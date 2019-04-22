package com.protogest.service.calendar.outlook.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.Date;

public interface OutlookService {

	@GET("/v1.0/me/calendarview")
	Call<PagedResult<Event>> getEvents(
            @Query("startdatetime") String periodStart,
            @Query("enddatetime") String periodEnd,
            @Query("select") String select
    );
}
