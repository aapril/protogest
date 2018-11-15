package com.pfe.ldb.auth.security;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtFilterConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

  private JwtProvider jwtProvider;

  
  public JwtFilterConfigurer(final JwtProvider jwtProvider) {
    this.jwtProvider = jwtProvider;
  }

  
  @Override
  public void configure(final HttpSecurity http) throws Exception {
    final JwtFilter customFilter = new JwtFilter(jwtProvider);
    
    http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
  }
}
