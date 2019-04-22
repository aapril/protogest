package com.protogest.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.protogest.dynamodb.DynamoDBManager;
import com.protogest.model.form.EFieldType;
import com.protogest.model.form.nosql.ProtocoleInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProtocoleInstanceRepository {

    public static void main(String[] args) {
        ProtocoleInstance p = new ProtocoleInstance();
        p.setUserID("asiudyiua2873287");

        List<ProtocoleInstance.FormField> fields = new ArrayList<>();
        fields.add(new ProtocoleInstance.FormField("1a", "Test", EFieldType.STRING));
        p.setFields(fields);
        save(p);

        String id = p.getFormUUID();
        ProtocoleInstance p2 = get(id);
        System.out.println();
        List<ProtocoleInstance> prots = getByUserId("asiudyiua2873287");
        System.out.println();

    }

    public static ProtocoleInstance save(ProtocoleInstance p) {
        UUID uuid = UUID.randomUUID();
        p.setFormUUID(uuid.toString());

        return update(p);
    }

    public static ProtocoleInstance update(ProtocoleInstance p) {

        DynamoDBMapper mapper = DynamoDBManager.mapper();
        mapper.save(p);

        System.out.println("Object was saved with id " + p.getFormUUID());
        return p;
    }

    public static ProtocoleInstance get(String id) {
        return DynamoDBManager.mapper().load(ProtocoleInstance.class, id);
    }

    public static List<ProtocoleInstance> list(List<String> ids) {
        List<ProtocoleInstance> protocoleInstances = new ArrayList<>();
        for(String id : ids) {
            protocoleInstances.add(get(id));
        }

        return protocoleInstances;
    }


    public static List<ProtocoleInstance> getByUserId(String id) {
        ProtocoleInstance protocoleInstance = new ProtocoleInstance();
        protocoleInstance.setUserID(id);

        DynamoDBQueryExpression<ProtocoleInstance> queryExpression
                = new DynamoDBQueryExpression<ProtocoleInstance>()
                .withIndexName("UserId-index")
                .withHashKeyValues(protocoleInstance)
                .withConsistentRead(false);

        return DynamoDBManager.mapper().query(ProtocoleInstance.class, queryExpression);
    }
}
