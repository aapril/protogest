package com.pfe.ldb.calendar.dao.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Column;
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

	
	@Column(name = "name")
	private @NonNull String name;
	
	@Column(name = "description")
	private @NonNull String description;
	
	@Column(name = "event_date")
	private @NonNull Date eventDate;
	
	@Column(name = "event_group_id")
	private @NonNull Integer eventGroupId;

	@Column(name = "member_id")
	private @NonNull Integer memberId;

	@Column(name = "event_state_id")
	private @NonNull Integer eventStateId;
}
