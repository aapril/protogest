package com.pfe.ldb.task.dao.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pfe.ldb.task.dao.entity.TaskGroupEntity;


@Repository
public interface TaskGroupRepository extends  CrudRepository<TaskGroupEntity, Integer> {

	List<TaskGroupEntity> findByEventId(final Integer eventId);

}
