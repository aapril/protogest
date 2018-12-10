package com.pfe.ldb.event.dao.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.OneToMany;
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
@Table(name = "eventState")
public class EventStateEntity extends AbstractEntity {

	@Column(name = "name")
	private @NonNull String name;
	
	@OneToMany(mappedBy = "eventState")
	private List<EventEntity> events;
}
