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

    public CognitoRequestResult signUp(String email, String password) {
        try {
            final SignUpResult result = this.cognitoClient.signUp(new SignUpRequest()
                    .withClientId(this.cognitoCredentials.getClientId())
                    .withUsername(email)
                    .withPassword(password));
            return new CognitoRequestResult(true, result.getCodeDeliveryDetails().toString());
        } catch (AWSCognitoIdentityProviderException e) {
            return new CognitoRequestResult(false, e.getErrorMessage(), e.getErrorCode());
        }
    }

    public CognitoRequestResult signIn(String email, String password) {
        final Map<String, String> parameters = new HashMap<>(2);
        parameters.put("USERNAME", email);
        parameters.put("PASSWORD", password);

        try {
            final AdminInitiateAuthResult result = this.cognitoClient.adminInitiateAuth(
                    new AdminInitiateAuthRequest()
                            .withAuthFlow(AuthFlowType.ADMIN_NO_SRP_AUTH)
                            .withAuthParameters(parameters)
                            .withUserPoolId(this.cognitoCredentials.getUserPoolId())
                            .withClientId(this.cognitoCredentials.getClientId())
            );


            if (result.getChallengeName() == null || result.getChallengeName().isEmpty()) {
                final AuthenticationResultType auth = result.getAuthenticationResult();
                final HashMap<String, String> payload = new HashMap<>(5);

                payload.put("ACCESS_TOKEN", auth.getAccessToken());
                payload.put("REFRESH_TOKEN", auth.getRefreshToken());
                payload.put("ID_TOKEN", auth.getIdToken());
                payload.put("TOKEN_TYPE", auth.getTokenType());
                payload.put("EXPIRES_IN", String.valueOf(auth.getExpiresIn()));


                return new CognitoRequestResult<Map>(true, "Logged in successfully.", payload);
            } else {
                return new CognitoRequestResult(false, "Need to reset password.", null);
            }
        } catch (AWSCognitoIdentityProviderException e) {
            return new CognitoRequestResult(false, e.getErrorMessage(), e.getErrorCode());
        }
    }
}
