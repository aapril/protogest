package com.protogest.service.database.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;

public class FieldApprobation {

    public FieldApprobation() {

    }

    public FieldApprobation(String formUUID, EValidationStatus status, String proposedToUserId) {
        this.formUUID = formUUID;
        this.validationStatus = status;
        this.proposedToUserId = proposedToUserId;
    }
    private String formUUID;
    private EValidationStatus validationStatus;
    private String proposedToUserId;

    @DynamoDBAttribute
    public String getFormUUID() {
        return formUUID;
    }

    public void setFormUUID(String formUUID) {
        this.formUUID = formUUID;
    }


    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute
    public EValidationStatus getValidationStatus() {
        return validationStatus;
    }

    public void setValidationStatus(EValidationStatus validationStatus) {
        this.validationStatus = validationStatus;
    }


    @DynamoDBAttribute
    public String getProposedToUserId() {
        return proposedToUserId;
    }

    public void setProposedToUserId(String proposedToUserId) {
        this.proposedToUserId = proposedToUserId;
    }
}
