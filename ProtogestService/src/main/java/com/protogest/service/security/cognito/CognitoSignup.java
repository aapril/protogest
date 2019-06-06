package com.protogest.service.security.cognito;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.ConfirmSignUpRequest;
import com.amazonaws.services.cognitoidp.model.SignUpRequest;
import com.amazonaws.services.cognitoidp.model.SignUpResult;
import com.protogest.model.SignupAttemptRequest;
import com.protogest.model.SignupAttemptResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CognitoSignup {

    static AWSCognitoIdentityProvider cognitoClient = AWSCognitoIdentityProviderClientBuilder.defaultClient();

    @RequestMapping(value = "/register")
    @ApiOperation(value = "Read protocol.", response = SignupAttemptResponse.class)
    public ResponseEntity<SignupAttemptResponse> Register(
            @RequestBody SignupAttemptRequest signupRequest
            ) {

        SignupAttemptResponse response = new SignupAttemptResponse();
        response.email = signupRequest.email;
        response.password = signupRequest.password;

        System.out.println("ATTEMPT TO REGISTER!");
        System.out.println("Registered email : " + signupRequest.email);
        System.out.println("Registered pwd : " + signupRequest.password);

        SignUpResult result = cognitoClient.signUp(new SignUpRequest()
                .withClientId(Environment.CLIENT_ID)
                .withUsername(signupRequest.email)
                .withPassword(signupRequest.password));

        return ResponseEntity.ok().body(response);
    }
}
