package com.pfe.ldb.vote.dao.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;

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
@Table(name = "voteState")
public class VoteStateEntity extends AbstractEntity {

	@Column(name = "name")
	private @NonNull String name;
	
	@Column(name = "description")
	private @NonNull String description;
}
