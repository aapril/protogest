package com.pfe.ldb.event.dao.entity;

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

	
	@JoinColumn(name = "name")
	private @NonNull String name;
	
	@JoinColumn(name = "description")
	private @NonNull String description;
	
	@JoinColumn(name = "event_date")
	private @NonNull Date eventDate;
	
	@ManyToOne
	@JoinColumn(name = "event_group_id")
	private @NonNull EventGroupEntity eventGroup;

	@JoinColumn(name = "member_id")
	private @NonNull Integer memberId;

	@ManyToOne
	@JoinColumn(name = "event_state_id")
	private @NonNull EventStateEntity eventState;
}
