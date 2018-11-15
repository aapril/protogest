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

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "userAuthorities")
public class UserAuthoritiesEntity extends AbstractEntity {

	@ManyToOne
	@JoinColumn(name = "userId")
	private @NonNull UserEntity user;

	@ManyToOne
	@JoinColumn(name = "authorityId")
	private @NonNull AuthoritiesEntity authority;
}
