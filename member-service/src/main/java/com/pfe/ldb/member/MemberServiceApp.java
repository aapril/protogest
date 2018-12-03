package com.pfe.ldb.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

import com.pfe.ldb.member.controller.MemberController;

@SpringBootApplication
@Import({ MemberController.class })
public class MemberServiceApp extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(MemberServiceApp.class, args);
	}
}