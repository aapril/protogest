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
@Table(name="vote")
public class VoteEntity extends AbstractEntity{

	private @NonNull String name;
	private @NonNull String description;
	
	@ManyToOne
	@JoinColumn(name = "voterId")
	private @NonNull MemberEntity member;
	
	@ManyToOne
	@JoinColumn(name = "eventId")
	private @NonNull EventEntity event;
	
	@ManyToOne
	@JoinColumn(name = "voteState")
	private @NonNull VoteStateEntity voteState;	
}
