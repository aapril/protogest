package com.protogest.service.calendar.outlook.auth;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TokenService {

	@FormUrlEncoded
	@POST("/{tenantid}/oauth2/v2.0/token")
	Call<Token> getAccessTokenFromAuthCode(
            @Path("tenantid") String tenantId,
            @Field("client_id") String clientId,
            @Field("client_secret") String clientSecret,
            @Field("grant_type") String grantType,
            @Field("code") String code,
            @Field("redirect_uri") String redirectUrl
    );
}
