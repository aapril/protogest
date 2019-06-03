package com.protogest.model;

import lombok.Data;

@Data
public class SignupAttemptRequest {
    public String email;
    public String password;
}
