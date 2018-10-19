package com.pfe.ldb.database.configuration;

import java.sql.SQLException;

import org.h2.server.web.WebServlet;
import org.h2.tools.Server;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@EntityScan("com.pfe.ldb.entities")
@Profile("dev")
public class DatabaseServiceConfiguration {

	@Bean
	public ServletRegistrationBean h2servletRegistration() {
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
		registrationBean.addUrlMappings("/console/*");
		return registrationBean;
	}

	@Bean
	public Server h2Server() throws SQLException {
		return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9091").start();
	}
}
