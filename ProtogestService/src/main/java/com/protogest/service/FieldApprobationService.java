package com.protogest.service;

import com.protogest.model.form.FieldApprobation;
import com.protogest.model.form.UserForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class FieldApprobationService {

//    private @Autowired
//    FieldApprobationRepository repository;

    public Map<String, List<FieldApprobation>> getFieldsToApproveForUser(String userId) {
        return null;
//        return repository.getPendingFieldByUserId(userId).stream()
//                .collect(
//                        Collectors.groupingBy(FieldApprobation::getFormUUID, Collectors.toCollection(ArrayList::new))
//                );
    }

    public void accept(String formUUID, String fieldId, String relatedUserId) {
//        String value = repository.accept(formUUID, fieldId, relatedUserId);
//
//
//        ProtocoleInstance protocoleInstance = ProtocoleInstanceRepository.get(formUUID);
//        for(ProtocoleInstance.FormField field : protocoleInstance.getFields()) {
//            if(field.getId().equals(fieldId)) {
//                field.setValue(value);
//                break;
//            }
//        }
    }
    public void refuse(String formUUID, String fieldId, String relatedUserId) {
//        repository.refuse(formUUID, fieldId, relatedUserId);
    }

    private String getProposedToUserId(String relatedUserId, UserForm userForm) {
        return null;
//        return userForm.getUserId().equals(relatedUserId) ? userForm.getRelatedUserId() : userForm.getUserId();
    }
}
