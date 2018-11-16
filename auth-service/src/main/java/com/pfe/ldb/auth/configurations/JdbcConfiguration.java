package com.pfe.ldb.auth.configurations;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan("org.baeldung.jdbc")
public class JdbcConfiguration {
	@Bean
	public DataSource mysqlDataSource() {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://lawyerdev.coh0yeelrta5.us-east-1.rds.amazonaws.com:3306/lawyer_db");
		dataSource.setUsername("root");
		dataSource.setPassword("4PROTOdb");

		return dataSource;
	}
}
