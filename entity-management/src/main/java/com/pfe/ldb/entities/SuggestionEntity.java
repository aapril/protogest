package com.pfe.ldb.entities;

import java.util.Date;

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
@Table(name = "suggestion")
public class SuggestionEntity extends AbstractEntity {

	private @NonNull String name;
	private @NonNull String description;
	private @NonNull Date suggestionDate;

	@ManyToOne
	private @NonNull EventMemberStateEntity eventUserDestination;
}
