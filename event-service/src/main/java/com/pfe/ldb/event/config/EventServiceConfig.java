package com.pfe.ldb.event.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.pfe.ldb.event.service.DefaultEventService;
import com.pfe.ldb.event.service.EventService;


@Configuration
@EntityScan("com.pfe.ldb.event.dao.entity")
@EnableJpaRepositories("com.pfe.ldb.event.dao.repository")
@Profile({"dev","prod"})
public class EventServiceConfig {

    @Bean
    public EventService eventService() {
    	return new DefaultEventService();
    }
    
    @Bean
    public ModelMapper modelMapper() {
    	return new ModelMapper();
    }
}
