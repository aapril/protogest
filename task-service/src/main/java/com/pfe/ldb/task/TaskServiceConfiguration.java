package com.pfe.ldb.task;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.pfe.ldb.task.facades.DefaultTaskFacade;
import com.pfe.ldb.task.facades.TaskFacade;
import com.pfe.ldb.task.services.DefaultTaskService;
import com.pfe.ldb.task.services.TaskService;

@Configuration
@ComponentScan(basePackages = { "com.pfe.ldb.task"})
@EntityScan("com.pfe.ldb.entities")
@EnableJpaRepositories("com.pfe.ldb.task.repositories")
@Profile({"dev", "prod"})
public class TaskServiceConfiguration {   

	@Bean
	public TaskService taskService() {
		return new DefaultTaskService();
	}
	
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    
    @Bean
    public TaskFacade taskFacade() {
    	return new DefaultTaskFacade();
    }
}
