package com.pfe.ldb.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="eventState")
public class EventStateEntity extends AbstractEntity {

	private String name;
	
	public EventStateEntity() {
		
	}
	
	public EventStateEntity(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
