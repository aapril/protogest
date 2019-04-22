package com.protogest.repository;

import com.protogest.model.form.UserForm;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class UserFormRepositoryCustomImpl implements UserFormRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<UserForm> findByFormUUID(String formUUID) {
        UserForm query = entityManager.find(UserForm.class, formUUID);

       return Optional.of(query);
    }

    @Override
    public List<String> getRelatedFormIds(String relatedUserId) {
        String sql = "select form_UUID from user_form where related_user_id = ?";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, relatedUserId);

        return query.getResultList();
    }
}
