package com.protogest.service.util;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("protogest")
public class config
{
    private static config globalConfig = new config();

    private final Email email = new Email();

    public static config get()
    {
        return globalConfig;
    }


    public Email getEmail() { return email; }


    public static class Email
    {
        private String region;
        private String from;

        public String getRegion()
        {
            return region;
        }

        public String getFrom()
        {
            return from;
        }

        public void setRegion(String emailRegion) { this.region = emailRegion; }
        public void setFrom(String emailFrom) { this.from = emailFrom; }
    }

}
