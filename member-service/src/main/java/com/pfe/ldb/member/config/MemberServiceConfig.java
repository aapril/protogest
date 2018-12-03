package com.pfe.ldb.member.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.pfe.ldb.member.controller.MemberController;
import com.pfe.ldb.member.service.DefaultMemberService;
import com.pfe.ldb.member.service.MemberService;


@Configuration
@Import({ MemberController.class })
@EntityScan("com.pfe.ldb.member.dao.entity")
@EnableJpaRepositories("com.pfe.ldb.member.dao.repository")
@Profile({"dev", "prod"})
public class MemberServiceConfig {   

	@Bean
	public MemberService memberService() {
		return new DefaultMemberService();
	}
	
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
