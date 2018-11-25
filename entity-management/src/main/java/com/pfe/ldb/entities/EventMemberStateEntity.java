package com.pfe.ldb.entities;

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
@Table(name = "eventUserDestination")
public class EventMemberStateEntity extends AbstractEntity {

	@ManyToOne
	private @NonNull EventStateEntity eventState;

	@ManyToOne
	private @NonNull EventEntity event;

	@ManyToOne
	private @NonNull MemberEntity member;
}