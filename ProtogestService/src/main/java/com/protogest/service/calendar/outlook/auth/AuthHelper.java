package com.protogest.service.calendar.outlook.auth;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component
public class AuthHelper {
    private static final String authority = "https://login.microsoftonline.com";
    private static final String authorizeUrl = authority + "/common/oauth2/v2.0/authorize";


    private static String[] scopes = {
            "openid",
            "offline_access",
            "profile",
            "User.Read",
            "Mail.Read",
            "Calendars.Read",
            "Contacts.Read"
    };


    private static String getAppId(Environment env) {
        return env.getProperty("msAuth.appId");
    }

    private static String getAppPassword(Environment env) {
        return env.getProperty("msAuth.appPassword");
    }

    private static String getRedirectUrl(Environment env) {
        return env.getProperty("msAuth.redirectUrl");
    }

    private static String getScopes() {
        StringBuilder sb = new StringBuilder();
        for (String scope : scopes) {
            sb.append(scope + " ");
        }
        return sb.toString().trim();
    }

    public static Token getTokenFromAuthCode(String authCode, String tenantId, String urlBase, Environment environment) {
        // Create a logging interceptor to log request and responses
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor).build();

        // Create and configure the Retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(authority)
                .client(client)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        // Generate the token service
        TokenService tokenService = retrofit.create(TokenService.class);

        String redirectUrl = urlBase + "authorize";

        try {
            return tokenService.getAccessTokenFromAuthCode(tenantId, getAppId(environment), getAppPassword(environment),
                    "authorization_code", authCode, redirectUrl).execute().body();
        } catch (IOException e) {
            Token error = new Token();
            error.setError("IOException");
            error.setErrorDescription(e.getMessage());
            return error;
        }
    }

}
