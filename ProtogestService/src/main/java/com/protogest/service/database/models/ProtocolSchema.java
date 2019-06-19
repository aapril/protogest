package com.protogest.service.database.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@DynamoDBTable(tableName = "protocol_schema")
public class ProtocolSchema {
    public ProtocolSchema() {
    }

    private String uuid;
    private String name;
    private String description;

    @SerializedName("protocol_fields")
    private List<ProtocolField> protocolFields;

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @DynamoDBAttribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute(attributeName = "protocol_fields")
    public List<ProtocolField> getProtocolFields() {
        return protocolFields;
    }

    public void setProtocolFields(List<ProtocolField> protocolFields) {
        this.protocolFields = protocolFields;
    }


    @DynamoDBAttribute
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @DynamoDBDocument
    public static class ProtocolField {
        private String sectionID;
        private String sectionName;
        private List<SubSection> subSection;

        public ProtocolField() {
        }

        public String getSectionID() {
            return sectionID;
        }

        public void setSectionID(String sectionID) {
            this.sectionID = sectionID;
        }

        public String getSectionName() {
            return sectionName;
        }

        public void setSectionName(String sectionName) {
            this.sectionName = sectionName;
        }

        public List<SubSection> getSubSection() {
            return subSection;
        }

        public void setSubSection(List<SubSection> subSection) {
            this.subSection = subSection;
        }
    }

    @DynamoDBDocument
    public static class SubSection {
        private String num;
        private String type;
        private String desc;
        private List<DateSection> dateSection;

        public SubSection() {
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public List<DateSection> getDateSection() {
            return dateSection;
        }

        public void setDateSection(List<DateSection> dateSection) {
            this.dateSection = dateSection;
        }
    }

    @DynamoDBDocument
    public static class DateSection {
        private String num;
        private String type;
        private String desc;

        public DateSection() {
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}
