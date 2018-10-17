package com.pfe.ldb.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="eventUserDestination")
public class EventUserDestinationEntity extends AbstractEntity {


	
	
	public EventUserDestinationEntity() {
		
	}
	public EventUserDestinationEntity(EventEntity event, String email, MemberEntity member) {
		super();
		this.event = event;
		this.email = email;
		this.member = member;
	}
	
	public EventUserDestinationEntity(EventEntity event, String email, MemberEntity member, EventStateEntity eventState) {
		super();
		this.event = event;
		this.email = email;
		this.member = member;
		this.eventState = eventState;
	}
	
	@ManyToOne
	@JoinColumn(name = "eventStateId")
	private EventStateEntity eventState;

	@ManyToOne
	@JoinColumn(name = "eventId")
	private EventEntity event;
	
	private String email;
	
	@ManyToOne
	@JoinColumn(name = "memberId")
	private MemberEntity member;

	public EventEntity getEvent() {
		return event;
	}

	public void setEvent(EventEntity event) {
		this.event = event;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public MemberEntity getMember() {
		return member;
	}

	public void setMember(MemberEntity member) {
		this.member = member;
	}
	public EventStateEntity getEventState() {
		return eventState;
	}
	public void setEventState(EventStateEntity eventState) {
		this.eventState = eventState;
	}
	
}
