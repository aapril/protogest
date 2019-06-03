package com.protogest.service.security.cognito;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.SignUpRequest;
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

//        SignUpRequest request = new SignUpRequest();
//        request.setUsername(username);
//        request.setPassword(password);
//        request.setClientId(Environment.CLIENT_ID);
//
//        cognitoClient.signUp(request);

        //return ResponseEntity.ok(response)
        return ResponseEntity.badRequest().body(response);
    }
}
