package com.pfe.ldb.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
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
@Table(name = "event")
public class EventEntity extends AbstractEntity {

	@ManyToOne
	private @NonNull EventGroupEntity eventGroup;

	@ManyToOne
	private @NonNull MemberEntity member;

	@ManyToOne
	private @NonNull EventStateEntity eventState;

	@OneToMany(mappedBy = "event")
	private List<TaskGroupEntity> taskGroup;

	private @NonNull String name;
	private @NonNull String description;
	private @NonNull Date eventDate;
}
