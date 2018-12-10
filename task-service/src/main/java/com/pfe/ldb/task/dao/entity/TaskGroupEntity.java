package com.pfe.ldb.task.dao.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.OneToMany;
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
@Table(name = "taskGroup")
public class TaskGroupEntity extends AbstractEntity {

	@Column(name = "name")
	private @NonNull String name;

	@Column(name = "description")
	private @NonNull String description;

	@ManyToOne
	@JoinColumn(name = "event_id")
	private EventEntity event;

	@OneToMany(mappedBy = "taskGroup")
	private List<TaskEntity> tasks;
}
