package com.pfe.ldb.event.dao.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
@Table(name = "ebventState")
public class EventAndEventStateEntity extends AbstractEntity {

	@ManyToOne
	@JoinColumn(name = "event_id")
	private @NonNull EventEntity event;

	@ManyToOne
	@JoinColumn(name = "state_id")
	private @NonNull EventStateEntity state;
}
