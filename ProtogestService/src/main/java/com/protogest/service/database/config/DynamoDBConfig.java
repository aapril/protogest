package com.protogest.service.database.config;


import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamoDBConfig {
    @Value("${dynamodb.access-key}")
    private String accessKey;

    @Value("${dynamodb.secret}")
    private String secret;

    @Value("${dynamodb.end-point}")
    private String endPoint;

    @Value("${dynamodb.region}")
    private String region;

    @Bean
    public AmazonDynamoDB getAmazonDynamoDB() {
        return AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(this.endPoint, this.region))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(this.accessKey, this.secret)))
                .build();
    }

    @Bean
    public DynamoDBMapper mapper(AmazonDynamoDB dynamoDB) {
        return new DynamoDBMapper(dynamoDB);
    }
}
