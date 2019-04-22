package com.protogest.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={
        "com.protogest.repository", "com.protogest.service"})
public class ProtogestApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProtogestApplication.class, args);
    }
}
