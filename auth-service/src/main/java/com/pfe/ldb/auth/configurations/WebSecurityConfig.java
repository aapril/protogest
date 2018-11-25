package com.pfe.ldb.auth.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(final HttpSecurity http) throws Exception {

		// Disable CSRF (cross site request forgery)
		http.csrf().disable();
		
		// Entry points
		http.authorizeRequests().antMatchers("/**").permitAll();
	}

	@Override
	public void configure(final WebSecurity web) throws Exception {

		web.ignoring().antMatchers("/v2/api-docs")
				.antMatchers("/swagger-resources/**")
				.antMatchers("/swagger-ui.html")
				.antMatchers("/configuration/**")
				.antMatchers("/webjars/**")
				.antMatchers("/public");
	}
}
