package com.pfe.ldb.calendar.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.pfe.ldb.calendar.service.CalendarService;
import com.pfe.ldb.calendar.service.DefaultCalendarService;

@Configuration
@EntityScan("com.pfe.ldb.calendar.dao.entity")
@EnableJpaRepositories("com.pfe.ldb.calendar.dao.repository")
@Profile({ "dev", "prod" })
public class CalendarServiceConfig {

	@Bean
	public CalendarService calendarService() {
		return new DefaultCalendarService();
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}