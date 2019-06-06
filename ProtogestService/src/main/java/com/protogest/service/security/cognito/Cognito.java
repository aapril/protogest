package com.protogest.service.security.cognito;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.AttributeType;
import com.amazonaws.services.cognitoidp.model.GetUserRequest;
import com.amazonaws.services.cognitoidp.model.GetUserResult;
import org.springframework.stereotype.Component;

@Component
public class Cognito {
    private final AWSCognitoIdentityProvider cognitoClient;

    public CognitoUtils(AWSCognitoIdentityProvider cognitoClient) {
        this.cognitoClient = cognitoClient;
    }

    public String getUserEmail(String token) {
        final GetUserRequest request = new GetUserRequest();
        request.setAccessToken(token);
        final GetUserResult result = this.cognitoClient.getUser(request);
        return result.getUserAttributes().stream().filter(attributeType -> attributeType.getName().contentEquals("email"))
                .findFirst().map(AttributeType::getValue).orElse(null);
    }

    public static void deleteUser(String userId, String userPoolId) {
        AdminDeleteUserRequest request = new AdminDeleteUserRequest();
        request.setUsername(userId);
        request.setUserPoolId(userPoolId);

        AdminDeleteUserResult result = cognitoClient.adminDeleteUser(request);
    }
}
