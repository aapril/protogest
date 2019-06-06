package com.protogest.service.security.cognito;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CognitoConfig {
    @Value("${dynamodb.access-key}")
    private String accessKey;

    @Value("${dynamodb.secret}")
    private String secret;

//    @Value("${dynamodb.end-point}")
//    private String endPoint;

    @Value("${dynamodb.region}")
    private String region;

    @Bean
    AWSCognitoIdentityProvider getIdentityIdentifier() {
        return AWSCognitoIdentityProviderClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(this.accessKey, this.secret)))
                .withRegion(this.region)
                .build();
    }
}
