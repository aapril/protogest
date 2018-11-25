package com.pfe.ldb.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

	private @NonNull String name;
	private @NonNull String description;
	
	@ManyToOne
	@JoinColumn(name = "eventId")
	private EventEntity event;
	
	@OneToMany(mappedBy = "taskGroup")
	private Set<TaskEntity> tasks;
}
