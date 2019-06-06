package com.protogest.service;

import com.protogest.model.form.FieldApprobation;
import com.protogest.model.form.ValidationField;
import com.protogest.service.calendar.CalendarService;
import com.protogest.service.database.models.ProtocolInstance;
import com.protogest.service.notification.EmailNotifier;
import com.protogest.service.security.cognito.Cognito;
import io.swagger.annotations.ApiOperation;
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
import java.util.Map;

@RestController
public class ProtocolInstanceController {
    
    private final ProtoService protoService;
    private final FieldApprobationService fieldApprobationService;
    private final ValidationFieldService validationFieldService;
    private final CalendarService calendarService;
    private final Cognito cognito;

    public ProtocolInstanceController(ProtoService protoService, FieldApprobationService fieldApprobationService, ValidationFieldService validationFieldService, CalendarService calendarService, Cognito cognito) {
        this.protoService = protoService;
        this.fieldApprobationService = fieldApprobationService;
        this.validationFieldService = validationFieldService;
        this.calendarService = calendarService;
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
    public ResponseEntity<Map<String, List<FieldApprobation>>> getFieldsToValidate(
            @RequestHeader("Authentification") String authToken) {
        return ResponseEntity.ok(fieldApprobationService.getFieldsToApproveForUser(cognito.getUserEmail(authToken)));
    }


    @PostMapping("/my/protocols")
    @ApiOperation(value = "Create protocol.", response = ProtocolInstance.class)
    public ResponseEntity<ProtocolInstance> addProtocol(@RequestHeader("Authentification") String authToken,
                                                        final @Validated @RequestBody ProtocolCreation proto) throws Exception {
        final String userMail = cognito.getUserEmail(authToken);
        String formUUID = protoService.create(proto.getProtocol(), userMail, proto.getRelatedUserId());
        if (proto.getRelatedUserId() != null) {
            EmailNotifier.senInvitationEmailTo(proto.getRelatedUserId());
        } else {
            throw new Exception("A colleague's email must be linked to create a court case.");
        }
        return ResponseEntity.created(new URI("/protocole-instance/" + formUUID)).body(proto.getProtocol());
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
    public ResponseEntity<ProtocolInstance> updateProtocol(
            @RequestHeader("Authentification") String authToken,
            final @PathVariable String formUUID,
            final @Validated @RequestBody ProtocolCreation proto) throws URISyntaxException {

        String userMail = cognito.getUserEmail(authToken);
        proto.getProtocol().setUserEmail(userMail);

        //TODO validate form belongs to user
        protoService.update(formUUID, proto.getProtocol());
        return ResponseEntity.created(new URI("/protocole-instance/" + formUUID)).body(proto.getProtocol());
    }

    @GetMapping("/my/protocols")
    @ApiOperation(value = "List protocols.", response = ProtocolInstance[].class)
    public ResponseEntity<List<ProtocolInstance>> listProtocols(@RequestHeader("Authentification") String authToken) {
        String email = cognito.getUserEmail(authToken);
        List<ProtocolInstance> protocols = protoService.list(email);
        return ResponseEntity.ok(protocols);
    }

    @GetMapping("/my/related-protocols")
    @ApiOperation(value = "List protocols.", response = ProtocolInstance[].class)
    public ResponseEntity<List<ProtocolInstance>> listRelatedProtocols(@RequestHeader("Authentification") String authToken) {
        String email = cognito.getUserEmail(authToken);
        List<ProtocolInstance> protocols = protoService.getInvitedProtocolInstances(email);
        return ResponseEntity.ok(protocols);
    }


    @PutMapping("/accept-field/{fieldId}/{formUUID}")
    @ApiOperation(value = "Accept field value in protocol.")
    public void acceptField(@RequestHeader("Authentification") String authToken,
                            final @PathVariable String fieldId,
                            final @PathVariable String formUUID) {
        String userMail = cognito.getUserEmail(authToken);

        fieldApprobationService.accept(formUUID, fieldId, userMail);
    }


    @PutMapping("/refuse-field/{fieldId}/{formUUID}")
    @ApiOperation(value = "Accept field value in protocol.")
    public void refuseField(@RequestHeader("Authentification") String authToken,
                            final @PathVariable String fieldId,
                            final @PathVariable String formUUID,
                            final @Validated @RequestBody RefuseField refuseField) {
        String userMail = cognito.getUserEmail(authToken);

        ValidationField validationField = new ValidationField();
        validationField.setProposedValue(refuseField.getProposedValue());
        validationField.setType(refuseField.getFieldType());
        validationField.setProposedById(userMail);
        validationField.setFieldId(fieldId);
        validationFieldService.addValidationField(validationField, formUUID);
        fieldApprobationService.refuse(formUUID, fieldId, userMail);
    }

    @GetMapping(value = "/protocols/{id}/download")
    @ApiOperation(value = "Returns ics file associated with protocol.")
    public ResponseEntity<InputStreamResource> getIcs(final @PathVariable String id) throws FileNotFoundException {
        File file = calendarService.createIcsFromProtocol(id);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header("Content-Disposition", String.format("inline; filename=\"Protocole d'instance.ics\""))
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }
}