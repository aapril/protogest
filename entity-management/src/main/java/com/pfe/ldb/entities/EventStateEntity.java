package com.pfe.ldb.entities;

import java.util.List;

import javax.persistence.Entity;
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

	private @NonNull String name;
	
	@OneToMany(mappedBy = "eventState")
	private List<EventEntity> events;
}
