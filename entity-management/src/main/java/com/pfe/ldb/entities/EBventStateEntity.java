package com.pfe.ldb.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ebventState")
public class EBventStateEntity extends AbstractEntity {
	
	@ManyToOne
	@JoinColumn(name = "eventId")
	private EventEntity event;
	
	@ManyToOne
	@JoinColumn(name = "stateId")
	private EventStateEntity state;
	
	public EBventStateEntity() {
		
	}
	
	public EBventStateEntity(EventEntity event, EventStateEntity state) {
		super();
		this.event = event;
		this.state = state;
	}

	public EventEntity getEvent() {
		return event;
	}

	public void setEvent(EventEntity event) {
		this.event = event;
	}

	public EventStateEntity getState() {
		return state;
	}

	public void setState(EventStateEntity state) {
		this.state = state;
	}
	
	

	

}
