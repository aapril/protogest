package com.protogest.service.schema;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.protogest.service.database.models.ProtocolSchema;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProtocolSchemaService {
    private final DynamoDBMapper mapper;

    public ProtocolSchemaService(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    public List<ProtocolSchema> getAllProtocolSchemas(){
        return mapper.scan(ProtocolSchema.class, new DynamoDBScanExpression());
    }
}
