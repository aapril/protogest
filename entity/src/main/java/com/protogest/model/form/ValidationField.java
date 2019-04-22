package com.protogest.model.form;

import com.protogest.model.AbstractEntity;
import com.protogest.model.form.nosql.ProtocoleInstance;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "validation_field")
public class ValidationField /*extends AbstractEntity*/ {

    public static ValidationField from(ProtocoleInstance.FormField field) {
        ValidationField validationField = new ValidationField();
        validationField.type = field.getType();
        validationField.proposedValue = field.getValue();
        validationField.fieldId = field.getId();
        return validationField;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "validation_field_id", updatable = false, nullable = false)
    protected Integer validationFieldId;

    @ManyToOne
    @JoinColumn(name = "form_UUID")
    private UserForm userForm;

    @Column(name = "field_id")
    private String fieldId;

    @Column(name = "proposed_by_user_id")
    private String proposedById;

    @Column(name = "proposed_value")
    private String proposedValue;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private EFieldType type;

    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL)
    private List<FieldApprobation> approbations = new ArrayList<>();


    public Integer getValidationFieldId() {
        return validationFieldId;
    }

    public void setValidationFieldId(Integer validationFieldId) {
        this.validationFieldId = validationFieldId;
    }

    public UserForm getUserForm() {
        return userForm;
    }

    public void setUserForm(UserForm userForm) {
        this.userForm = userForm;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getProposedById() {
        return proposedById;
    }

    public void setProposedById(String proposedById) {
        this.proposedById = proposedById;
    }

    public String getProposedValue() {
        return proposedValue;
    }

    public void setProposedValue(String proposedValue) {
        this.proposedValue = proposedValue;
    }

    public EFieldType getType() {
        return type;
    }

    public void setType(EFieldType type) {
        this.type = type;
    }

    public List<FieldApprobation> getApprobations() {
        return approbations;
    }

    public void setApprobations(List<FieldApprobation> approbations) {
        this.approbations = approbations;
    }

    public void addAppobation(FieldApprobation approbation) {
        approbations.add(approbation);
    }
}
