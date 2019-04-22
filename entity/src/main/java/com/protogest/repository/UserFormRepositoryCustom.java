package com.protogest.repository;

import com.protogest.model.form.UserForm;

import java.util.List;
import java.util.Optional;

public interface UserFormRepositoryCustom {

    Optional<UserForm> findByFormUUID(String formUUID);
    List<String> getRelatedFormIds(String relatedUserId);
}
