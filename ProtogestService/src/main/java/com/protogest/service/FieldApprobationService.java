package com.protogest.service;

import com.protogest.model.form.FieldApprobation;
import com.protogest.model.form.UserForm;
import com.protogest.model.form.ValidationField;
import com.protogest.model.form.nosql.ProtocoleInstance;
import com.protogest.repository.FieldApprobationRepository;
import com.protogest.repository.ProtocoleInstanceRepository;
import com.protogest.repository.UserFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class FieldApprobationService {

    private @Autowired
    FieldApprobationRepository repository;

    public Map<String, List<FieldApprobation>> getFieldsToApproveForUser(String userId) {
        return repository.getPendingFieldByUserId(userId).stream()
                .collect(
                        Collectors.groupingBy(FieldApprobation::getFormUUID, Collectors.toCollection(ArrayList::new))
                );
    }

    public void accept(String formUUID, String fieldId, String relatedUserId) {
        String value = repository.accept(formUUID, fieldId, relatedUserId);


        ProtocoleInstance protocoleInstance = ProtocoleInstanceRepository.get(formUUID);
        for(ProtocoleInstance.FormField field : protocoleInstance.getFields()) {
            if(field.getId().equals(fieldId)) {
                field.setValue(value);
                break;
            }
        }
    }
    public void refuse(String formUUID, String fieldId, String relatedUserId) {
        repository.refuse(formUUID, fieldId, relatedUserId);
    }

    private String getProposedToUserId(String relatedUserId, UserForm userForm) {
        return userForm.getUserId().equals(relatedUserId) ? userForm.getRelatedUserId() : userForm.getUserId();
    }
}
