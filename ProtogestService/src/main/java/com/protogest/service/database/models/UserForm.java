package com.protogest.service.database.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.protogest.model.form.ValidationField;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@DynamoDBTable(tableName = "user_form")
public class UserForm implements Serializable/*extends AbstractEntity*/ {

    @Column(name = "user_id")
    private String userId;

    @Column(name = "related_user_id")
    private String relatedUserId;

    @Id
    @Column(name = "form_UUID")
    private String formUUID;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "userForm", cascade = CascadeType.ALL)
    private List<ValidationField> validationFields;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFormUUID() {
        return formUUID;
    }

    public void setFormUUID(String formUUID) {
        this.formUUID = formUUID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ValidationField> getValidationFields() {
        return validationFields;
    }

    public void setValidationFields(List<ValidationField> validationFields) {
        this.validationFields = validationFields;
    }

    public String getRelatedUserId() {
        return relatedUserId;
    }

    public void setRelatedUserId(String relatedUserId) {
        this.relatedUserId = relatedUserId;
    }
}
