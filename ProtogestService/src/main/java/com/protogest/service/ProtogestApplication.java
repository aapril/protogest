package com.protogest.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Configuration;

//@SpringBootApplication(scanBasePackages={
//        "com.protogest.repository", "com.protogest.service"})
@Configuration
@Import({AopAutoConfiguration.class,
    CodecsAutoConfiguration.class,
    DataSourceAutoConfiguration.class,
    DataSourceTransactionManagerAutoConfiguration.class,
    DispatcherServletAutoConfiguration.class,
    EmbeddedWebServerFactoryCustomizerAutoConfiguration.class,
    ErrorMvcAutoConfiguration.class,
    HibernateJpaAutoConfiguration.class,
    HttpEncodingAutoConfiguration.class,
    HttpMessageConvertersAutoConfiguration.class,
    JacksonAutoConfiguration.class,
    JdbcTemplateAutoConfiguration.class,
    JmxAutoConfiguration.class,
    JtaAutoConfiguration.class,
    PersistenceExceptionTranslationAutoConfiguration.class,
    PropertyPlaceholderAutoConfiguration.class,
    RestTemplateAutoConfiguration.class,
    ServletWebServerFactoryAutoConfiguration.class,
    SpringDataWebAutoConfiguration.class,
    TransactionAutoConfiguration.class,
    ValidationAutoConfiguration.class,
    WebMvcAutoConfiguration.class,
    WebSocketServletAutoConfiguration.class})
public class ProtogestApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(ProtogestApplication.class, args);
    }
}
