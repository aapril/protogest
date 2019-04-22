package com.protogest.repository;

import com.protogest.model.form.EValidationStatus;
import com.protogest.model.form.FieldApprobation;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;

import java.util.List;

import static com.protogest.model.form.EValidationStatus.*;

@Repository
@Transactional
public class FieldApprobationRepositoryImpl implements FieldApprobationRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<FieldApprobation> getPendingFieldByUserId(String userId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<FieldApprobation> cq = cb.createQuery(FieldApprobation.class);

        Root<FieldApprobation> field = cq.from(FieldApprobation.class);
        Predicate userIdPredicate = cb.equal(field.get("proposedToUserId"), userId);
        Predicate pendingPredicate = cb.equal(field.get("validationStatus"), PENDING);
        cq.where(userIdPredicate, pendingPredicate);

        TypedQuery<FieldApprobation> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    public String getApprovedValue(String formUUID, String fieldId) {
        String sql = "SELECT proposed_value FROM protogest.validation_field VF\n" +
                "natural JOIN field_approbation FA\n" +
                "where field_id = ? and form_UUID = ? and validation_status = '" + APPROVED.name() + "'\n";
        Query query = entityManager.createNativeQuery(sql);

        query.setParameter(1,fieldId);
        query.setParameter(2, formUUID);

        return (String) query.getSingleResult();
    }

    @Override
    public String accept(String formUUID, String fieldId, String relatedUserId) {
        String sql = "update field_approbation set validation_status = '" + APPROVED.name() +"' where field_approbation_id = (\n" +
                "SELECT field_approbation_id FROM (\n" +
                "SELECT field_approbation_id FROM protogest.validation_field VF\n" +
                "natural JOIN field_approbation FA\n" +
                "where field_id = ? and form_UUID = ? and proposed_to_user_id = ? and validation_status = 'PENDING'\n" +
                ") AS A\n" +
                ");";

        Query query = entityManager.createNativeQuery(sql);

        query.setParameter(1,fieldId);
        query.setParameter(2, formUUID);
        query.setParameter(3, relatedUserId);

        query.executeUpdate();
        return getApprovedValue(formUUID, fieldId);
    }

    @Override
    public void refuse(String formUUID, String fieldId, String relatedUserId) {
        String sql = "update field_approbation set validation_status = '" + REFUSED.name() + "' where field_approbation_id = (\n" +
                "SELECT field_approbation_id FROM (\n" +
                "SELECT field_approbation_id FROM protogest.validation_field VF\n" +
                "natural JOIN field_approbation FA\n" +
                "where field_id = ? and form_UUID = ? and proposed_to_user_id = ? and validation_status = 'PENDING'\n" +
                ") AS A\n" +
                ");";

        String value = getApprovedValue(formUUID,fieldId);

        Query query = entityManager.createNativeQuery(sql);

        query.setParameter(1,fieldId);
        query.setParameter(2, formUUID);
        query.setParameter(3, relatedUserId);

        query.executeUpdate();
    }
}
