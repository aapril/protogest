package com.pfe.ldb.event;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.pfe.ldb.event.services.DefaultEventService;
import com.pfe.ldb.event.services.EventService;


@Configuration
@ComponentScan(basePackages = { "com.pfe.ldb.event"})
@EntityScan("com.pfe.ldb.entity")
@EnableJpaRepositories("com.pfe.ldb.event.repositories")
@Profile({"dev","prod"})
public class EventServiceConfiguration {

    @Bean
    public EventService eventService() {
    	return new DefaultEventService();
    }
    
    @Bean
    public ModelMapper modelMapper() {
    	return new ModelMapper();
    }
}
