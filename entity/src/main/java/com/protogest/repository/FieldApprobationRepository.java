package com.protogest.repository;

import com.protogest.model.form.FieldApprobation;
import com.protogest.model.form.UserForm;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldApprobationRepository extends CrudRepository<FieldApprobation, Long>, FieldApprobationRepositoryCustom {
}
