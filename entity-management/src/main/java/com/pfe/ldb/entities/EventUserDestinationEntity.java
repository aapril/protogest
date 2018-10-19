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
@Table(name="eventUserDestination")
public class EventUserDestinationEntity extends AbstractEntity {
	
	private @NonNull String email;
	
	@ManyToOne
	@JoinColumn(name = "eventStateId")
	private @NonNull EventStateEntity eventState;

	@ManyToOne
	@JoinColumn(name = "eventId")
	private @NonNull EventEntity event;	
	
	@ManyToOne
	@JoinColumn(name = "memberId")
	private @NonNull MemberEntity member;
}
