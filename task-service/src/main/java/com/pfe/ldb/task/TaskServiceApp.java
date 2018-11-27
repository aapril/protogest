package com.pfe.ldb.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class TaskServiceApp {

	public static void main(String[] args) {
		SpringApplication.run(TaskServiceApp.class, args);
	}
}
