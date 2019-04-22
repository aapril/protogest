package com.protogest.service.calendar.outlook.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import static java.util.Base64.getUrlDecoder;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IdToken {

    @JsonProperty("tid")
    private String tenantId;

    public static IdToken parseEncodedToken(String encodedToken) {
        // Encoded token is in three parts, separated by '.'
        String[] tokenParts = encodedToken.split("\\.");

        // The three parts are: header.token.signature
        String idToken = tokenParts[1];

        byte[] decodedBytes = getUrlDecoder().decode(idToken);

        ObjectMapper mapper = new ObjectMapper();
        IdToken newToken = null;
        try {
            newToken = mapper.readValue(decodedBytes, IdToken.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newToken;
    }

    public String getTenantId() {
        return tenantId;
    }

}
