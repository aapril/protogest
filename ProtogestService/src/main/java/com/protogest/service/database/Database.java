package com.protogest.service.database;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.protogest.model.form.EFieldType;
import com.protogest.model.form.nosql.ProtocoleInstance;
import com.protogest.service.database.models.*;
import edu.emory.mathcs.backport.java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Database {
    private static final Logger logger = LoggerFactory.getLogger(Database.class);

    private final static List<Class<?>> models = Arrays.asList(new Class<?>[]{TestTable.class, ValidationField.class});


    public Database(AmazonDynamoDB dynamoDB, DynamoDBMapper mapper) {
        setupTables(dynamoDB, mapper);

        ValidationField from = ValidationField.from(new ProtocoleInstance.FormField("test", "testingvalue", EFieldType.STRING));
        UserForm userForm = new UserForm();
        userForm.setStatus("test-status");
        from.setUserForm(userForm);
        for (int i = 0; i < 10; i++) {
            from.addAppobation(new FieldApprobation(i + "",
                    (i % 2)== 0 ?EValidationStatus.PENDING:EValidationStatus.APPROVED
                    , "user" + i));
        }
        mapper.save(from);
        List<ValidationField> fields = mapper.scan(ValidationField.class, new DynamoDBScanExpression());
        int r = 0;
    }

    private void setupTables(AmazonDynamoDB dynamoDB, DynamoDBMapper mapper) {
        for (Class<?> model : models) {
            CreateTableRequest tableRequest = createTableRequest(mapper, model);
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
