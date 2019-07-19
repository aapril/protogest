package com.protogest.service;

import com.protogest.service.calendar.CalendarService;
import com.protogest.service.database.models.ProtocolInstance;
import com.protogest.service.notification.EmailNotifier;
import com.protogest.service.users.CognitoRequestResult;
import com.protogest.service.users.CognitoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.core.env.Environment;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class ProtocolInstanceController {

    private final ProtoService protoService;
    private final CalendarService calendarService;
    private final Environment env;
    private final CognitoService cognito;

    public ProtocolInstanceController(ProtoService protoService, CalendarService calendarService, Environment env, CognitoService cognito) {
        this.protoService = protoService;
        this.calendarService = calendarService;
        this.env = env;
        this.cognito = cognito;
    }

    @GetMapping("/test")
    public ResponseEntity test() {
        protoService.getByUUID("test");
        return ResponseEntity.ok("ok!");
    }

    @GetMapping("/auth")
    public ResponseEntity auth(@RequestHeader("Authentification") String authToken) {
        return ResponseEntity.ok(cognito.getUserEmail(authToken));
    }


    @GetMapping("/")
    public ResponseEntity health() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/my/to-validate")
    public ResponseEntity<String> getFieldsToValidate(
            @RequestHeader("Authentification") String authToken) {
//        return ResponseEntity.ok(fieldApprobationService.getFieldsToApproveForUser(cognito.getInvitedEmails(authToken)));
        return null;
    }


    @PostMapping("/my/protocols")
    @ApiOperation(value = "Create protocol.", response = ProtocolInstance.class)
    public ResponseEntity addProtocol(@RequestHeader("Authentification") String authToken,
                                      final @Validated @RequestBody ProtocolInstance protocol) throws Exception {
        final CognitoRequestResult<String> emailResult = cognito.getUserEmail(authToken);
        if (!emailResult.isSuccess()) {
            return ResponseEntity.badRequest().body(emailResult);
        }
        final String formUUID = protoService.create(protocol, emailResult.getPayload());
        if (protocol.getInvitedEmails() != null && protocol.getInvitedEmails().size() > 0) {
            protocol.getInvitedEmails().forEach(EmailNotifier::senInvitationEmailTo);
        } else {
            throw new Exception("A colleague's email must be linked to create a court case.");
        }
        return ResponseEntity.created(new URI("/protocole-instance/" + formUUID)).body(protocol);
    }

    @PostMapping("my/protocols/edit/{formUUID}")
    @ApiOperation(value = "Edit protocol.", response = ProtocolInstance.class)
    public @ResponseBody
    ResponseEntity editProtocol(
            @RequestHeader("Authentification") String authToken,
            final @PathVariable String formUUID,
            final @Validated @RequestBody ProtocolUpdate protocolUpdate) throws URISyntaxException {
        protoService.update(protocolUpdate);
        return ResponseEntity.ok("Updated");
    }

    @GetMapping("/my/protocols/{formUUID}")
    @ApiOperation(value = "Read protocol.", response = ProtocolInstance.class)
    public ResponseEntity<ProtocolInstance> updateProtocol(
            @RequestHeader("Authentification") String authToken,
            final @PathVariable String formUUID) {
        cognito.getUserEmail(authToken);

        return ResponseEntity.ok(protoService.getByUUID(formUUID));
    }

    @PutMapping("/my/protocols/{formUUID}")
    @ApiOperation(value = "Update protocol.", response = ProtocolInstance.class)
    public ResponseEntity updateProtocol(
            @RequestHeader("Authentification") String authToken,
            final @PathVariable String formUUID,
            final @Validated @RequestBody ProtocolCreation proto) throws URISyntaxException {

        final CognitoRequestResult<String> emailResult = cognito.getUserEmail(authToken);
        if (!emailResult.isSuccess()) {
            return ResponseEntity.badRequest().body(emailResult);
        }
        final String email = emailResult.getPayload();
        proto.getProtocol().setUserEmail(email);

        //TODO validate form belongs to user
        // protoService.update(formUUID, proto.getProtocol());
        return ResponseEntity.created(new URI("/protocole-instance/" + formUUID)).body(proto.getProtocol());
    }

    @GetMapping("/my/protocols")
    @ApiOperation(value = "List protocols.", response = ProtocolInstance[].class)
    public ResponseEntity listProtocols(@RequestHeader("Authentification") String authToken) {
        final CognitoRequestResult<String> emailResult = cognito.getUserEmail(authToken);
        if (!emailResult.isSuccess()) {
            return ResponseEntity.badRequest().body(emailResult);
        }
        final String email = emailResult.getPayload();

        final List<ProtocolInstance> protocols = protoService.getUserProtocols(email);
        return ResponseEntity.ok(protocols);
    }

    @GetMapping("/my/related-protocols")
    @ApiOperation(value = "List protocols.", response = ProtocolInstance[].class)
    public ResponseEntity listRelatedProtocols(@RequestHeader("Authentification") String authToken) {
        final CognitoRequestResult<String> emailResult = cognito.getUserEmail(authToken);
        if (!emailResult.isSuccess()) {
            return ResponseEntity.badRequest().body(emailResult);
        }
        final String email = emailResult.getPayload();
        List<ProtocolInstance> protocols = protoService.getInvitedProtocolInstances(email);
        return ResponseEntity.ok(protocols);
    }


    @PutMapping("/accept-field/{fieldId}/{formUUID}")
    @ApiOperation(value = "Accept field value in protocol.")
    public void acceptField(@RequestHeader("Authentification") String authToken,
                            final @PathVariable String fieldId,
                            final @PathVariable String formUUID) {
//        String userMail = cognito.getInvitedEmails(authToken);
//
//        fieldApprobationService.accept(formUUID, fieldId, userMail);
    }


    @PutMapping("/refuse-field/{fieldId}/{formUUID}")
    @ApiOperation(value = "Accept field value in protocol.")
    public void refuseField(@RequestHeader("Authentification") String authToken,
                            final @PathVariable String fieldId,
                            final @PathVariable String formUUID,
                            final @Validated @RequestBody RefuseField refuseField) {
//        String userMail = cognito.getUserEmail(authToken);
//
//        ValidationField validationField = new ValidationField();
//        validationField.setProposedValue(refuseField.getProposedValue());
//        validationField.setType(refuseField.getFieldType());
//        validationField.setProposedById(userMail);
//        validationField.setFieldId(fieldId);
//        validationFieldService.addValidationField(validationField, formUUID);
//        fieldApprobationService.refuse(formUUID, fieldId, userMail);
    }

    @GetMapping(value = "/protocols/{id}/download")
    @ApiOperation(value = "Returns ics file associated with protocol.")
    public ResponseEntity<InputStreamResource> getIcs(final @PathVariable String id) throws FileNotFoundException {
        final ProtocolInstance protocolInstance = protoService.getByUUID(id);
        File file = calendarService.createIcsFromProtocol(protocolInstance);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header("Content-Disposition", String.format("inline; filename=\"Protocole d'instance.ics\""))
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }

    @DeleteMapping(value = "/user")
    @ApiOperation(value = "Deletes a user from Cognito")
    public void deleteUser(@RequestHeader("Authentification") String authToken) {
        String userId = cognito.getUserId(authToken);
        cognito.deleteUser(userId, env.getProperty("cognito.userPoolId"));
    }
}
