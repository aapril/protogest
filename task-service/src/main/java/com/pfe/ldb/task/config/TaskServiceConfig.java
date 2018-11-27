package com.pfe.ldb.task.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.pfe.ldb.task.service.DefaultTaskService;
import com.pfe.ldb.task.service.TaskService;

@Configuration
@EntityScan("com.pfe.ldb.task.dao.entity")
@EnableJpaRepositories("com.pfe.ldb.task.dao.repository")
@Profile({"dev", "prod"})
public class TaskServiceConfig {   

	@Bean
	public TaskService taskService() {
		return new DefaultTaskService();
	}
	
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
