package com.protogest.service;

import com.protogest.model.form.nosql.ProtocoleInstance;

public class ProtocolCreation {

    private ProtocoleInstance protocol;
    private String relatedUserId;

    public ProtocoleInstance getProtocol() {
        return protocol;
    }

    public void setProtocol(ProtocoleInstance protocol) {
        this.protocol = protocol;
    }

    public String getRelatedUserId() {
        return relatedUserId;
    }

    public void setRelatedUserId(String relatedUserId) {
        this.relatedUserId = relatedUserId;
    }
}
