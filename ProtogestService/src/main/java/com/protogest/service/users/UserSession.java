package com.protogest.service.users;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.protogest.service.database.models.Session;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserSession {
    private final DynamoDBMapper mapper;

    public UserSession(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    public String create(Session session, String authToken, String email) {
        session.setAuthToken(authToken);
        session.setEmail(email);
        session.setUuid(authToken);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        session.setConnectionDate(dateFormat.format(date));

        mapper.save(session);

        return session.getUuid();
    }
}
