package com.pfe.ldb.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="eventGroup")
public class EventGroupEntity extends AbstractEntity {


	private String name;
	private String description;
	
	
	public EventGroupEntity() {
		
	}
	
	
	public EventGroupEntity(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
