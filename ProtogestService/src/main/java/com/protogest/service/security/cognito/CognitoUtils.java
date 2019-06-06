package com.protogest.service.security.cognito;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.GetUserRequest;
import com.amazonaws.services.cognitoidp.model.GetUserResult;

public class CognitoUtils {
    static AWSCognitoIdentityProvider cognitoClient = AWSCognitoIdentityProviderClientBuilder.defaultClient();

    public static String getUserEmail(String token)
    {
        GetUserRequest request = new GetUserRequest();
        request.setAccessToken(token);

        GetUserResult result = cognitoClient.getUser(request);

        return result.getUsername();
    }

    public static void deleteUser(String userId, String userPoolId) {
        AdminDeleteUserRequest request = new AdminDeleteUserRequest();
        request.setUsername(userId);
        request.setUserPoolId(userPoolId);

        AdminDeleteUserResult result = cognitoClient.adminDeleteUser(request);
    }
}
