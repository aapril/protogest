package com.pfe.ldb.entities;

import javax.persistence.OneToMany;

import java.util.Set;

import javax.persistence.Entity;
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

	private @NonNull String name;
	private @NonNull String description;
	
	@OneToMany(mappedBy = "eventGroup", orphanRemoval = true)
	private Set<EventEntity> events;
}
