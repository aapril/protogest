package com.pfe.ldb.vote.dao.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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

	private @NonNull String name;
	private @NonNull String description;

	@ManyToOne
	private @NonNull VoteStateEntity voteState;
}
