package com.pfe.ldb.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class TaskServiceApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(TaskServiceApplication.class, args);
	}
}
