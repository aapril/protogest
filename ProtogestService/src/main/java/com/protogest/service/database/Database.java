package com.protogest.service.database;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.protogest.service.database.models.ProtocolInstance;
import edu.emory.mathcs.backport.java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Database {
    private static final Logger logger = LoggerFactory.getLogger(Database.class);

    private final static List<Class<?>> models = Arrays.asList(new Class<?>[]{ProtocolInstance.class});


    public Database(AmazonDynamoDB dynamoDB, DynamoDBMapper mapper) {
        setupTables(dynamoDB, mapper);
    }

    private void setupTables(AmazonDynamoDB dynamoDB, DynamoDBMapper mapper) {
        for (Class<?> model : models) {
            logger.info("Setting up dynamoDB table `{}`", model.getCanonicalName());
            CreateTableRequest tableRequest = createTableRequest(mapper, model)
                    .withProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
            TableUtils.createTableIfNotExists(dynamoDB, tableRequest);
            try {
                TableUtils.waitUntilExists(dynamoDB, tableRequest.getTableName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private CreateTableRequest createTableRequest(DynamoDBMapper mapper, Class<?> klass) {
        CreateTableRequest request = mapper.generateCreateTableRequest(klass);
        request.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
        return request;
    }
}
