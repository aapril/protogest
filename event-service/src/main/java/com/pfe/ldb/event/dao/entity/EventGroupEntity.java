package com.pfe.ldb.event.dao.entity;

import javax.persistence.OneToMany;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Column;
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
@Table(name = "eventGroup")
public class EventGroupEntity extends AbstractEntity {

	@Column(name = "name")
	private @NonNull String name;

	@Column(name = "description")
	private @NonNull String description;

	@OneToMany(mappedBy = "eventGroup")
	private List<EventEntity> events;
}
