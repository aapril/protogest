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

    public String create(ProtocolInstance protocolInstance, String userEmail, String invitedUserEmail) {
        protocolInstance.setUserEmail(userEmail);
        protocolInstance.setInvitedUserEmail(invitedUserEmail);
        protocolInstance.setStatus(ProtocolInstance.Status.PENDING);

        mapper.save(protocolInstance);

        return protocolInstance.getUuid();
    }

    public ProtocolInstance getByUUID(String UUID) {
        return this.mapper.load(ProtocolInstance.class, UUID);
    }


    public List<ProtocolInstance> list(String userMail) {
        return getProtocolsByAttribute("userEmail", userMail);
    }

    public List<ProtocolInstance> getInvitedProtocolInstances(String userMail) {
        return getProtocolsByAttribute("invitedUserEmail", userMail);
    }

    private List<ProtocolInstance> getProtocolsByAttribute(String attributeName, String attributeValue) {
        final Map<String, AttributeValue> values = new HashMap<>();
        values.put(String.format(":%s", attributeName), new AttributeValue().withS(attributeValue));
        return this.mapper.scan(ProtocolInstance.class, new DynamoDBScanExpression()
                .withFilterExpression(String.format("%s = :%s", attributeName, attributeName))
                .withExpressionAttributeValues(values));
    }


    public void update(String formUUID, ProtocolInstance protocol) {
        // wasn't working
    }
}
