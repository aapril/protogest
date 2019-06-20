package com.protogest.service.users;

public class CognitoCredentials {
    private final String userPoolId;
    private final String clientId;

    public CognitoCredentials(String userPoolId, String clientId) {
        this.userPoolId = userPoolId;
        this.clientId = clientId;
    }

    public String getUserPoolId() {
        return userPoolId;
    }

    public String getClientId() {
        return clientId;
    }
}
