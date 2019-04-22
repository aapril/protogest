package com.protogest.repository;

import com.protogest.model.form.ValidationField;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValidationFieldRepository extends CrudRepository<ValidationField, Long> {
}
