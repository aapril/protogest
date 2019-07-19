package com.protogest.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.protogest.service.database.models.ProtocolInstance;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ProtoService {
    private final DynamoDBMapper mapper;

    public ProtoService(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    public String create(ProtocolInstance protocolInstance, String userEmail) {
        protocolInstance.setUserEmail(userEmail);
        protocolInstance.setStatus(ProtocolInstance.Status.PENDING);

        mapper.save(protocolInstance);

        return protocolInstance.getUuid();
    }

    public ProtocolInstance getByUUID(String UUID) {
        return this.mapper.load(ProtocolInstance.class, UUID);
    }


    public List<ProtocolInstance> getUserProtocols(String userMail) {
        return getProtocolsByAttribute(new ScanRequest("userEmail", userMail, false));
    }

    public List<ProtocolInstance> getInvitedProtocolInstances(String userMail) {
        return getProtocolsByAttribute(new ScanRequest("invitedEmails", userMail, true));
    }

    private List<ProtocolInstance> getProtocolsByAttribute(ScanRequest scanRequest) {
        final Map<String, AttributeValue> values = new HashMap<>();
        values.put(String.format(":%s", scanRequest.attribute), new AttributeValue().withS(scanRequest.value));
        final DynamoDBScanExpression expression = new DynamoDBScanExpression();
        if (scanRequest.useContains) {
            expression.withFilterExpression(String.format("contains(%s, :%s)", scanRequest.attribute, scanRequest.attribute));
        } else {
            expression.withFilterExpression(String.format("%s = :%s", scanRequest.attribute, scanRequest.attribute));
        }
        return this.mapper.scan(ProtocolInstance.class, expression.withExpressionAttributeValues(values));
    }


    public String update(ProtocolUpdate protocolUpdate) {
        ProtocolInstance protocolInstance = mapper.load(ProtocolInstance.class, protocolUpdate.getUuid());

        protocolInstance.setFields(protocolUpdate.getFields());

        mapper.save(protocolInstance);

        return "blah";
    }

    private class ScanRequest {
        final String attribute;
        final String value;
        final boolean useContains;

        public ScanRequest(String attribute, String value, boolean useContains) {
            this.attribute = attribute;
            this.value = value;
            this.useContains = useContains;
        }
    }
}
