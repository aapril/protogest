package com.protogest.model.form;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "field_approbation")
public class FieldApprobation /*extends AbstractEntity*/ {

    public FieldApprobation() {

    }

    public FieldApprobation(String formUUID, ValidationField field, EValidationStatus status,
                            String proposedToUserId) {
        this.formUUID = formUUID;
        this.field = field;
        this.validationStatus = status;
        this.proposedToUserId = proposedToUserId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "field_approbation_id")
    private Integer id;

    @Column(name = "form_UUID")
    private String formUUID;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "validation_field_id")
    private ValidationField field;

    @Enumerated(EnumType.STRING)
    @Column(name = "validation_status")
    private EValidationStatus validationStatus;

    @Column(name = "proposed_to_user_id")
    private String proposedToUserId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFormUUID() {
        return formUUID;
    }

    public void setFormUUID(String formUUID) {
        this.formUUID = formUUID;
    }

    public ValidationField getField() {
        return field;
    }

    public void setField(ValidationField field) {
        this.field = field;
    }

    public EValidationStatus getValidationStatus() {
        return validationStatus;
    }

    public void setValidationStatus(EValidationStatus validationStatus) {
        this.validationStatus = validationStatus;
    }

    public String getProposedToUserId() {
        return proposedToUserId;
    }

    public void setProposedToUserId(String proposedToUserId) {
        this.proposedToUserId = proposedToUserId;
    }

    public String getFieldValue() {
        return field.getProposedValue();
    }

    public EFieldType getFieldType() {
        return field.getType();
    }

    public String getFieldId() {
        return field.getFieldId();
    }
}
