package com.protogest.service;

import com.protogest.service.database.models.ProtocolInstance;

import java.util.List;

public class ProtocolUpdate {
    private String uuid;
    private List<ProtocolInstance.FormField> fields;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<ProtocolInstance.FormField> getFields() {
        return fields;
    }

    public void setFields(List<ProtocolInstance.FormField> fields) {
        this.fields = fields;
    }
}
