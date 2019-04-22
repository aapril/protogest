package com.protogest.repository;

import com.protogest.model.form.FieldApprobation;
import com.protogest.model.form.UserForm;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface FieldApprobationRepositoryCustom {

    List<FieldApprobation> getPendingFieldByUserId(String userId);

    String accept(String formUUID, String fieldId, String relatedUserId);
    void refuse(String formUUID, String fieldId, String relatedUserId);
}
