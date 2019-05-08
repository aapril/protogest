package com.protogest.service;

import com.protogest.model.form.*;
import com.protogest.model.form.nosql.ProtocoleInstance;
import com.protogest.repository.ProtocoleInstanceRepository;
import com.protogest.repository.UserFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProtoService {

    private @Autowired
    UserFormRepository repository;

    public String create(ProtocoleInstance proto, String userId, String relatedUserId) {

        String formUUID = ProtocoleInstanceRepository.save(proto).getFormUUID();

        UserForm userForm = new UserForm();
        userForm.setFormUUID(formUUID);
        userForm.setUserId(userId);
        userForm.setStatus("PENDING");
        userForm.setRelatedUserId(relatedUserId);

        List<ValidationField> validationFields = buildValidationFields(proto, userForm, relatedUserId);
        userForm.setValidationFields(validationFields);

        repository.save(userForm);

        proto.setUserID(userForm.getUserId());
        proto.setFormUUID(formUUID);

        return formUUID;
    }

    public ProtocoleInstance getByUUID(String UUID) {
        return ProtocoleInstanceRepository.get(UUID);
    }

    private List<ValidationField> buildValidationFields(ProtocoleInstance proto, UserForm userForm, String relatedUserId) {

        List<ValidationField> fieldsToValidate = proto.getFields()
                .stream()
                .filter(p -> p.getType() == EFieldType.DATE)
                .map(f -> ValidationField.from(f))
                .collect(Collectors.toList());

        fieldsToValidate.forEach(f -> {
//            f.setFormUUID(proto.getFormUUID());
            f.setProposedById(userForm.getUserId());
            f.setUserForm(userForm);
            f.addAppobation(new FieldApprobation(proto.getFormUUID(), f, EValidationStatus.PENDING, relatedUserId));
        });

        return fieldsToValidate;
    }

    public List<ProtocoleInstance> list(String userMail) {
        return ProtocoleInstanceRepository.getByUserId(userMail);
    }

    public List<String> getRelatedFormIds(String userMail) {
        return repository.getRelatedFormIds(userMail);
    }

    public void update(String formUUID, ProtocoleInstance protocol) {
        ProtocoleInstance original = ProtocoleInstanceRepository.get(formUUID);
        if(original != null) {
            protocol.setFormUUID(formUUID);
            ProtocoleInstanceRepository.update(protocol);
        }
    }
}
