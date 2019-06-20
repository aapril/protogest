package com.protogest.service.users;

public class CognitoRequestResult<Payload> {
    final boolean success;
    final String message;
    final Payload paylaod;

    public CognitoRequestResult(boolean success, String message, Payload paylaod) {
        this.success = success;
        this.message = message;
        this.paylaod = paylaod;
    }

    public CognitoRequestResult(boolean success, String message) {
        this.success = success;
        this.message = message;
        this.paylaod = null;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Payload getPaylaod() {
        return paylaod;
    }
}
