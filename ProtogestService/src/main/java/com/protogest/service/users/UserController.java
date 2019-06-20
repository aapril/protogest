package com.protogest.service.users;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final CognitoService cognitoService;

    public UserController(CognitoService cognitoService) {
        this.cognitoService = cognitoService;
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
}
