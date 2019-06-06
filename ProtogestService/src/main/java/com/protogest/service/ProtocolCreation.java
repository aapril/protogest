package com.protogest.service;


import com.protogest.service.database.models.ProtocolInstance;

public class ProtocolCreation {

    private ProtocolInstance protocol;
    private String relatedUserId;

    public ProtocolInstance getProtocol() {
        return protocol;
    }

    public void setProtocol(ProtocolInstance protocol) {
        this.protocol = protocol;
    }

    public String getRelatedUserId() {
        return relatedUserId;
    }

    public void setRelatedUserId(String relatedUserId) {
        this.relatedUserId = relatedUserId;
    }
}
