package com.pfe.ldb.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="event")
public class EventEntity extends AbstractEntity {


	private String name;
	private String description;
	private Date eventDate;
	
	
	@ManyToOne
	@JoinColumn(name = "eventGroupId")
	private EventGroupEntity eventGroup;
	
	
	@ManyToOne
	@JoinColumn(name = "taskId")
	private TaskEntity task;

	@ManyToOne
	@JoinColumn(name = "sourceId")
	private MemberEntity member;
	
	@ManyToOne
	@JoinColumn(name = "eventStateId")
	private EventStateEntity eventState;
	
	public EventEntity() {
		
	}
	
	public EventEntity(String name, String description, Date eventDate,EventGroupEntity eventGroup, TaskEntity task, MemberEntity member, EventStateEntity eventState) {
		super();
		this.name = name;
		this.description = description;
		this.eventDate = eventDate;
		this.eventGroup = eventGroup;
		this.task = task;
		this.member = member;
		this.eventState = eventState;
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


	public Date getEventDate() {
		return eventDate;
	}


	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}


	public TaskEntity getTask() {
		return task;
	}


	public void setTask(TaskEntity task) {
		this.task = task;
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

	public EventGroupEntity getEventGroup() {
		return eventGroup;
	}

	public void setEventGroup(EventGroupEntity eventGroup) {
		this.eventGroup = eventGroup;
	}
	
}
