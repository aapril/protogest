package com.protogest.service.users;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
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


    public String getUserFirstName(String userId) {


        AdminGetUserRequest  userRequest  = new AdminGetUserRequest().withUserPoolId("ca-central-1_ZBpO9AVc6").withUsername(userId);

        AdminGetUserResult userResult = cognitoClient.adminGetUser(userRequest);

        String firstName = null;

        for (int i = 0; i < userResult.getUserAttributes().size(); i++) {
            if(userResult.getUserAttributes().get(i).getName().equals("name")){
                firstName = userResult.getUserAttributes().get(i).getValue();
            }
        }

        return firstName;
    }

    public String getUserLastName(String userId) {
        AdminGetUserRequest  userRequest  = new AdminGetUserRequest().withUserPoolId("ca-central-1_ZBpO9AVc6").withUsername(userId);

        AdminGetUserResult userResult = cognitoClient.adminGetUser(userRequest);

        String lastName = null;

        for (int i = 0; i < userResult.getUserAttributes().size(); i++) {
            System.out.println(userResult.getUserAttributes().get(i).getName());
            if(userResult.getUserAttributes().get(i).getName().equals("family_name")){
                System.out.println("test"+userResult.getUserAttributes().get(i).getName());
                lastName = userResult.getUserAttributes().get(i).getValue();
            }
        }

        return lastName;
    }

    public void changePassword(String authToken, String oldPassword, String newPassword) {

        ChangePasswordRequest  passwordRequest = new ChangePasswordRequest()
                .withAccessToken(authToken)
                .withPreviousPassword(oldPassword)
                .withProposedPassword(newPassword);

        cognitoClient.changePassword(passwordRequest);


    }

    public void forgotPassword(String userName) {

        ForgotPasswordRequest  forgotpasswordRequest = new ForgotPasswordRequest().withUsername(userName);

        cognitoClient.forgotPassword(forgotpasswordRequest);


    }

    public  void setUserInfo(String userId,  String firstName, String lastName) {
        AdminUpdateUserAttributesRequest  userRequest  = new AdminUpdateUserAttributesRequest().withUserPoolId("ca-central-1_ZBpO9AVc6").withUsername(userId);


        Collection<AttributeType> attribute = new ArrayList<AttributeType>();
        AttributeType attributeFirstName = new AttributeType();
        attributeFirstName.setName("name");
        attributeFirstName.setValue(firstName);
        attribute.add(attributeFirstName);
        AttributeType attributeLastName = new AttributeType();
        attributeLastName.setName("family_name");
        attributeLastName.setValue(lastName);
        attribute.add(attributeLastName);
        userRequest.withUserAttributes(attribute);
        AdminUpdateUserAttributesResult userResult = cognitoClient.adminUpdateUserAttributes(userRequest);
    }

    public static void resetUserPassword(String userId, String userPoolId) {
        AdminResetUserPasswordRequest request = new AdminResetUserPasswordRequest();
        request.setUsername(userId);
        request.setUserPoolId(userPoolId);
        System.out.print(userId);
    }
}
