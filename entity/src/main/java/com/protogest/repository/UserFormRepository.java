package com.protogest.repository;

import com.protogest.model.form.UserForm;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFormRepository extends CrudRepository<UserForm, Long>, UserFormRepositoryCustom {
}
