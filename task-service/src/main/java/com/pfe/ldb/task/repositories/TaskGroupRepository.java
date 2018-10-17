package com.pfe.ldb.task.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.pfe.ldb.entities.TaskGroupEntity;

public interface TaskGroupRepository extends  CrudRepository<TaskGroupEntity,Integer> {

	List<TaskGroupEntity> findAllByParentId(Integer parentId);
}
