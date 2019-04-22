package com.protogest.service.config;

import com.protogest.service.ProtoService;
import com.protogest.service.calendar.CalendarService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EntityScan("com.protogest.model.form")
@EnableJpaRepositories("com.protogest.repository")
@EnableSwagger2
//@Profile({"dev", "prod"})
public class ServiceConfig {

    @Bean
    public ProtoService protoService() { return new ProtoService(); }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public CalendarService calendarService() {
        return new CalendarService();
    }
}
