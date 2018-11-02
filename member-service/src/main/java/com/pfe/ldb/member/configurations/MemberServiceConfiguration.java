package com.pfe.ldb.member.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.pfe.ldb.member.services.DefaultMemberService;
import com.pfe.ldb.member.services.MemberService;
@Configuration
@EntityScan("com.pfe.ldb.entities")
@EnableJpaRepositories("com.pfe.ldb.member.repositories")
@Profile({"dev", "prod"})
public class MemberServiceConfiguration {   

	@Bean
	public MemberService taskService() {
		return new DefaultMemberService();
	}
	
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}