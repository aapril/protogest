package com.protogest.service.users;

import com.protogest.service.database.models.ProtocolInstance;
import com.protogest.service.database.models.Session;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class UserController {
    private final CognitoService cognitoService;
    private final UserSession userSession;
    private final CognitoService cognito;

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

    @RequestMapping(method = RequestMethod.POST, value = "/users/login")
    public @ResponseBody
    ResponseEntity signIn(@RequestParam("email") String email,
                          @RequestParam("password") String password) {
        return ResponseEntity.ok().body(this.cognitoService.signIn(email, password));
    }

    @PostMapping("/store/session")
    @ApiOperation(value = "Create user session.", response = Session.class)
    public ResponseEntity<Session> storeSession(@RequestHeader("Authentification") String authToken,
                                final @Validated @RequestBody UserCreation user) throws Exception {
        final String userMail = cognito.getUserEmail(authToken);
        String sessionUUID = this.userSession.create(user.getSession(), authToken, userMail);

        return ResponseEntity.created(new URI("/protocole-instance/" + sessionUUID)).body(user.getSession());
    }
}
