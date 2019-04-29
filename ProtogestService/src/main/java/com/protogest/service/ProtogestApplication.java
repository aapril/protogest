package com.protogest.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages={
        "com.protogest.repository", "com.protogest.service"})
public class ProtogestApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(ProtogestApplication.class, args);
    }
}
