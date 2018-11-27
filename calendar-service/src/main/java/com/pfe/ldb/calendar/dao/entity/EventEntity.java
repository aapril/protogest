package com.pfe.ldb.calendar.dao.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
	
	@JoinColumn(name = "event_group_id")
	private @NonNull Integer eventGroupId;

	@JoinColumn(name = "member_id")
	private @NonNull Integer memberId;

	@JoinColumn(name = "event_state_id")
	private @NonNull Integer eventStateId;
}
