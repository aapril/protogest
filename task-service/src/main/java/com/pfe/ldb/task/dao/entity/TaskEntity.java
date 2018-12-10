package com.pfe.ldb.task.dao.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "task")
public class TaskEntity extends AbstractEntity {

	@Column(name = "name")
	private @NonNull String name;

	@Column(name = "description")
	private @NonNull String description;

	@ManyToOne
	@JoinColumn(name = "task_group_id")
	private @NonNull TaskGroupEntity taskGroup;
}
