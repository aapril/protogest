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
@Table(name = "userAuthorities")
public class UserAuthoritiesEntity extends AbstractEntity {

	@ManyToOne
	private @NonNull UserEntity user;

	@ManyToOne
	private @NonNull AuthorityEntity authority;
}
