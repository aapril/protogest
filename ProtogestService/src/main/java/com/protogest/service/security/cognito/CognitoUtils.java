package com.protogest.service.security.cognito;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClient;
import com.amazonaws.services.cognitoidp.model.GetUserRequest;
import com.amazonaws.services.cognitoidp.model.GetUserResult;

public class CognitoUtils {
    static AWSCognitoIdentityProviderClient cognitoClient = new AWSCognitoIdentityProviderClient();

    public static String getUserEmail(String token)
    {
        GetUserRequest request = new GetUserRequest();
        request.setAccessToken(token);

        GetUserResult result = cognitoClient.getUser(request);

        return result.getUsername();
    }
}
