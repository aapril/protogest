package com.protogest.service.users;

import com.protogest.service.database.models.Session;
import io.swagger.annotations.ApiOperation;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
public class UserController {
    private final CognitoService cognitoService;
    private final UserSession userSession;
    private final CognitoService cognito;
    private @Autowired Environment env;

    public UserController(CognitoService cognitoService, UserSession userSession, CognitoService cognito) {
        this.cognitoService = cognitoService;
        this.userSession = userSession;
        this.cognito = cognito;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users/signup")
    public @ResponseBody
    ResponseEntity signUp(@RequestParam("email") String email,
                          @RequestParam("password") String password) {
        return ResponseEntity.ok().body(this.cognitoService.signUp(email, password));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users/confirm_signup")
    public @ResponseBody
    ResponseEntity confirmSignUp(@RequestParam("email") String email,
                                 @RequestParam("code") String code) {
        return ResponseEntity.ok().body(this.cognitoService.confirmSignUp(email, code));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users/login")
    public @ResponseBody
    ResponseEntity signIn(@RequestParam("email") String email,
                          @RequestParam("password") String password) {
        return ResponseEntity.ok().body(this.cognitoService.signIn(email, password));
    }

    @PostMapping("/store/session")
    @ApiOperation(value = "Create user session.", response = Session.class)
    public ResponseEntity storeSession(@RequestHeader("Authentification") String authToken,
                                       final @Validated @RequestBody UserCreation user) throws Exception {
        final CognitoRequestResult<String> emailResult = cognito.getUserEmail(authToken);
        if (!emailResult.isSuccess()) {
            return ResponseEntity.badRequest().body(emailResult);
        }
        final String email = emailResult.getPayload();
        String sessionUUID = this.userSession.create(user.getSession(), authToken, email);

        return ResponseEntity.created(new URI("/protocole-instance/" + sessionUUID)).body(user.getSession());
    }


    @GetMapping("/user/getAttribute")
    @ApiOperation(value = "get a user attribute from Cognito")
    public JSONArray getUserData(@RequestHeader("Authentification") String authToken) {

        String userId = this.cognitoService.getUserId(authToken);
        JSONArray attribute = new JSONArray();
        JSONObject item = new JSONObject();
        item.put("name",this.cognitoService.getUserFirstName(userId));
        item.put("familyName",this.cognitoService.getUserLastName(userId));
        attribute.add(item);
        return attribute;
    }

    @PostMapping("/user/changePassword")
    @ApiOperation(value = "change password from Cognito")
    public void changePassword(@RequestHeader("Authentification") String authToken,
                               final @RequestBody Map<String,String> attributeBody) {

        String userMail = this.cognitoService.getUserId(authToken);
        this.cognitoService.changePassword(authToken,attributeBody.get("oldPassword"),attributeBody.get("newPassword"));
    }

    @GetMapping("/user/resetPass")
    @ApiOperation(value = "reset user password from Cognito")
    public String resetUserPassword(@RequestHeader("Authentification") String authToken) {

        String userId = this.cognitoService.getUserId(authToken);

        this.cognitoService.resetUserPassword(userId, env.getProperty("cognito.userPoolId"));
        return "test";
    }

    @PostMapping("/user/forgotPassword")
    @ApiOperation(value = "forgot password from Cognito")
    public void forgotPassword(@RequestHeader(value = "Authentification", required=false) String authToken,
                               final @RequestBody Map<String,String> attributeBody) {
        System.out.print(attributeBody.get("userName"));
        this.cognitoService.forgotPassword(attributeBody.get("userName"));
    }

    @PostMapping("/user/resetPasswordCode")
    @ApiOperation(value = "reset password with code from Cognito")
    public void resetPasswordCode(@RequestHeader(value = "Authentification", required=false) String authToken,
                               final @RequestBody Map<String,String> attributeBody) {
        System.out.print(attributeBody.get("password"));
        System.out.print(attributeBody.get("code"));
        this.cognitoService.resetPasswordCode(attributeBody.get("password"), attributeBody.get("code"), attributeBody.get("userName"));
    }


    @PostMapping("/user/setAttribute")
    @ApiOperation(value = "set attribute in cognito.")
    public void setAttribute(@RequestHeader("Authentification") String authToken,
                             final @RequestBody Map<String,String> attributeBody) {
        System.out.print("test");
        String userMail = this.cognitoService.getUserId(authToken);
        System.out.println(attributeBody.get("firstName")+"test");
        System.out.println(attributeBody.get("lastName")+"test");
        System.out.println(userMail+"test");
        //this.cognitoService.setUserInfo(userMail,attributeBody.get("firstName"),attributeBody.get("lastName"));
    }

}
