package com.protogest.service;

import com.protogest.model.form.EValidationStatus;
import com.protogest.model.form.FieldApprobation;
import com.protogest.model.form.UserForm;
import com.protogest.model.form.ValidationField;
import com.protogest.model.form.nosql.ProtocoleInstance;
import com.protogest.repository.FieldApprobationRepository;
import com.protogest.repository.ProtocoleInstanceRepository;
import com.protogest.repository.UserFormRepository;
import com.protogest.repository.ValidationFieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ValidationFieldService {

    private @Autowired
    UserFormRepository userFormRepository;

    private @Autowired
    ValidationFieldRepository validationFieldRepository;

    public void addValidationField(ValidationField field, String formUUID) {
        UserForm userForm = userFormRepository.findByFormUUID(formUUID).get();
        field.setUserForm(userForm);
        String proposedToUserId = getProposedToUserId(field.getProposedById(), userForm);

        FieldApprobation approbation = new FieldApprobation(formUUID, field, EValidationStatus.PENDING, proposedToUserId);
        field.getApprobations().add(approbation);

        validationFieldRepository.save(field);

        ProtocoleInstance protocoleInstance = ProtocoleInstanceRepository.get(formUUID);
        for(ProtocoleInstance.FormField formField : protocoleInstance.getFields()) {
            if(formField.getId().equals(field.getFieldId())) {
                formField.setValue(field.getProposedValue());
                ProtocoleInstanceRepository.update(protocoleInstance);
                break;
            }
        }
    }


    private String getProposedToUserId(String relatedUserId, UserForm userForm) {
        return userForm.getUserId().equals(relatedUserId) ? userForm.getRelatedUserId() : userForm.getUserId();
    }
}
