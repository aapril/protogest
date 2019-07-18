package com.protogest.service.users;

public class CognitoRequestResult<Payload> {
    final boolean success;
    final String message;
    final String code;
    Payload payload;

    public CognitoRequestResult(boolean success, String message, Payload payload) {
        this.success = success;
        this.message = message;
        this.code = null;
        this.payload = payload;
    }

    public CognitoRequestResult(boolean success, String message, String code) {
        this.success = success;
        this.message = message;
        this.code = code;
        this.payload = null;
    }

    public CognitoRequestResult(boolean success, String message) {
        this.success = success;
        this.message = message;
        this.code = null;
        this.payload = null;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    public String getCode() {
        return code;
    }
}
