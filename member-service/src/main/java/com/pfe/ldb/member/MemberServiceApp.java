package com.pfe.ldb.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MemberServiceApp extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(MemberServiceApp.class, args);
	}
}
