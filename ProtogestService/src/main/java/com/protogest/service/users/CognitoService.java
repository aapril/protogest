package com.protogest.service.users;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CognitoService {
    private final AWSCognitoIdentityProvider cognitoClient;
    private final CognitoCredentials cognitoCredentials;

    public CognitoService(AWSCognitoIdentityProvider cognitoClient, CognitoCredentials cognitoCredentials) {
        this.cognitoClient = cognitoClient;
        this.cognitoCredentials = cognitoCredentials;
    }

    public String getUserEmail(String token) {
        final GetUserRequest request = new GetUserRequest();
        request.setAccessToken(token);
        final GetUserResult result = this.cognitoClient.getUser(request);
        return result.getUserAttributes().stream().filter(attributeType -> attributeType.getName().contentEquals("email"))
                .findFirst().map(AttributeType::getValue).orElse(null);
    }

    public String getUserId(String token) {
        GetUserRequest request = new GetUserRequest();
        request.setAccessToken(token);

        GetUserResult result = cognitoClient.getUser(request);

        return result.getUsername();
    }

    public void deleteUser(String userId, String userPoolId) {
        AdminDeleteUserRequest request = new AdminDeleteUserRequest();
        request.setUsername(userId);
        request.setUserPoolId(userPoolId);

        AdminDeleteUserResult result = cognitoClient.adminDeleteUser(request);
    }
}
