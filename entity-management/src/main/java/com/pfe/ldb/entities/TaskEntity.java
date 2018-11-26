package com.pfe.ldb.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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

	@JoinColumn(name = "name")
	private @NonNull String name;

	@JoinColumn(name = "description")
	private @NonNull String description;

	@ManyToOne
	@JoinColumn(name = "task_group_id")
	private @NonNull TaskGroupEntity taskGroup;
}
