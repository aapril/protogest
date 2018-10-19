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

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name="event")
public class EventEntity extends AbstractEntity {

	private @NonNull String name;
	private @NonNull String description;
	private @NonNull Date eventDate;
	
	@ManyToOne
	@JoinColumn(name = "eventGroupId")
	private @NonNull EventGroupEntity eventGroup;
	
	
	@ManyToOne
	@JoinColumn(name = "taskId")
	private TaskEntity task;

	@ManyToOne
	@JoinColumn(name = "sourceId")
	private MemberEntity member;
	
	@ManyToOne
	@JoinColumn(name = "eventStateId")
	private EventStateEntity eventState;
}
