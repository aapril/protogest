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


@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name="task")
public class TaskEntity extends AbstractEntity {

	private @NonNull String name;
	private @NonNull String description;
	
	@ManyToOne
	@JoinColumn(name = "taskGroupId")
	private @NonNull TaskGroupEntity taskGroup;
}
