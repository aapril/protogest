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
@Table(name="ebventState")
public class EBventStateEntity extends AbstractEntity {
	
	@ManyToOne
	@JoinColumn(name = "eventId")
	private @NonNull EventEntity event;
	
	@ManyToOne
	@JoinColumn(name = "stateId")
	private @NonNull EventStateEntity state;
}
