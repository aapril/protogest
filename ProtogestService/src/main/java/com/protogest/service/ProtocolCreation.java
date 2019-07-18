package com.protogest.service;


import com.protogest.service.database.models.ProtocolInstance;

public class ProtocolCreation {
    private ProtocolInstance protocol;
    private String[] invitedEmails;

    public ProtocolInstance getProtocol() {
        return protocol;
    }

    public void setProtocol(ProtocolInstance protocol) {
        this.protocol = protocol;
    }

    public String[] getInvitedEmails() {
        return invitedEmails;
    }

    public void setInvitedEmails(String[] invitedEmails) {
        this.invitedEmails = invitedEmails;
    }
}
