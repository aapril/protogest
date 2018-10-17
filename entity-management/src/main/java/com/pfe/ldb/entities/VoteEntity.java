package com.pfe.ldb.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="vote")
public class VoteEntity extends AbstractEntity{


	private String name;
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "voterId")
	private MemberEntity member;
	
	@ManyToOne
	@JoinColumn(name = "eventId")
	private EventEntity event;
	
	@ManyToOne
	@JoinColumn(name = "voteState")
	private VoteStateEntity voteState;
	
	public VoteEntity() {
		
	}
	
	public VoteEntity(String name, String description, MemberEntity member, EventEntity event,
			VoteStateEntity voteState) {
		super();
		this.name = name;
		this.description = description;
		this.member = member;
		this.event = event;
		this.voteState = voteState;
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

	public MemberEntity getMember() {
		return member;
	}

	public void setMember(MemberEntity member) {
		this.member = member;
	}

	public EventEntity getEvent() {
		return event;
	}

	public void setEvent(EventEntity event) {
		this.event = event;
	}

	public VoteStateEntity getVoteState() {
		return voteState;
	}

	public void setVoteState(VoteStateEntity voteState) {
		this.voteState = voteState;
	}

	
}
