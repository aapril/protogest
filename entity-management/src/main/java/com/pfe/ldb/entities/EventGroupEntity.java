package com.pfe.ldb.entities;

import javax.persistence.Entity;
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
@Table(name="eventGroup")
public class EventGroupEntity extends AbstractEntity {

	private @NonNull String name;
	private @NonNull String description;
}
