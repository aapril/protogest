package com.protogest.model.form.nosql;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.protogest.model.form.EFieldType;

import java.util.*;

@DynamoDBTable(tableName = "formulaire_dev")
public class ProtocoleInstance {

    @DynamoDBHashKey(attributeName = "FormUUID")
    private String formUUID;

    @DynamoDBAttribute
    private List<FormField> fields;

    @DynamoDBIndexHashKey(attributeName = "UserId", globalSecondaryIndexName = "UserId-index")
    private String userID;



    @DynamoDBDocument
    public static class FormField {
        private String id;
        private String value;
        private String desc;
        private EFieldType type;

        public FormField() {
            // ctor for DynamoDBMapper
        }

        public FormField(String id, String value, String desc, EFieldType type) {
            this.id = id;
            this.value = value;
            this.type = type;
            this.desc = desc;
        }

        @DynamoDBAttribute(attributeName = "Id")
        public String getId() { return id; }
        public void setId(String id) { this.id = id; }

        @DynamoDBAttribute(attributeName = "Value")
        public String getValue() { return value; }
        public void setValue(String value) { this.value = value; }

        @DynamoDBAttribute(attributeName = "Desc")
        public String getDesc() { return desc; }
        public void setDesc(String desc) { this.desc = desc; }

        @DynamoDBTypeConvertedEnum
        @DynamoDBAttribute(attributeName = "Type")
        public EFieldType getType() { return type; }
        public void setType(EFieldType type) { this.type = type; }

    }

    public String getFormUUID() {
        return formUUID;
    }


    public void setFormUUID(String formUUID) {
        this.formUUID = formUUID;
    }

    public List<FormField> getFields() {
        return fields;
    }

    public void setFields(List<FormField> fields) {
        this.fields = fields;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

}
