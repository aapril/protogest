package com.pfe.ldb.event.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import com.pfe.ldb.entities.EventGroupEntity;
import org.springframework.stereotype.Repository;

@Repository
@Transactional(readOnly = true)
public interface EventGroupRepository extends CrudRepository<EventGroupEntity, Integer> {
}
