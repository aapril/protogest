package com.pfe.ldb.auth.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.pfe.ldb.auth.services.DefaultUserService;
import com.pfe.ldb.auth.services.UserService;

@Configuration
@EntityScan("com.pfe.ldb.entities")
@EnableJpaRepositories("com.pfe.ldb.auth.repositories")
@Profile({"dev", "prod"})
public class AuthServiceConfiguration {

	@Bean
	public UserService userService() {
		return new DefaultUserService();
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
