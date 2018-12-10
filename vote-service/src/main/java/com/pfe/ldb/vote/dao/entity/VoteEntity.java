package com.pfe.ldb.vote.dao.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
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
@Table(name = "vote")
public class VoteEntity extends AbstractEntity {

	@Column(name = "name")
	private @NonNull String name;
	
	@Column(name = "description")
	private @NonNull String description;

	@ManyToOne
	@JoinColumn(name = "vote_state_id")
	private @NonNull VoteStateEntity voteState;
}
