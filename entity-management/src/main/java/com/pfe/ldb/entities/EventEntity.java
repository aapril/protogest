package com.pfe.ldb.entities;

import java.util.Date;

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
@Table(name = "event")
public class EventEntity extends AbstractEntity {

	@ManyToOne
	@JoinColumn(name = "eventGroupId")
	private @NonNull EventGroupEntity eventGroup;

	@ManyToOne
	@JoinColumn(name = "taskId")
	private @NonNull TaskEntity task;

	@ManyToOne
	@JoinColumn(name = "sourceId")
	private @NonNull MemberEntity author;

	@ManyToOne
	@JoinColumn(name = "eventStateId")
	private @NonNull EventStateEntity eventState;

	private @NonNull String name;
	private @NonNull String description;
	private @NonNull Date eventDate;
}
