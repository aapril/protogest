package com.protogest.service;

import com.protogest.model.form.EFieldType;

public class RefuseField {
    /*
    {
	"proposedValue":"2020-01-01",
	"type":"DATE"
}
     */

    private String proposedValue;
    private EFieldType fieldType;

    public String getProposedValue() {
        return proposedValue;
    }

    public void setProposedValue(String proposedValue) {
        this.proposedValue = proposedValue;
    }

    public EFieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(EFieldType fieldType) {
        this.fieldType = fieldType;
    }
}
