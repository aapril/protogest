package com.pfe.ldb.repositories;

import org.springframework.data.repository.CrudRepository;
import com.pfe.ldb.entities.EventGroupEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface EventGroupRepository extends CrudRepository<EventGroupEntity, Integer> {
}
