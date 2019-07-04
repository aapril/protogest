package com.protogest.service.schema;

import com.protogest.service.database.models.ProtocolSchema;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProtocolSchemaController {
    private final ProtocolSchemaService protocolSchemaService;

    public ProtocolSchemaController(ProtocolSchemaService protocolSchemaService) {
        this.protocolSchemaService = protocolSchemaService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/protocol-schemas")
    public @ResponseBody
    List<ProtocolSchema> getAllProtocolsSchemas() {
        return protocolSchemaService.getAllProtocolSchemas();
    }

}
