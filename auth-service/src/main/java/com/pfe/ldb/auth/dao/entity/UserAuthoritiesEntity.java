package com.pfe.ldb.auth.dao.entity;

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
	@JoinColumn(name = "user_id")
	private @NonNull UserEntity user;

	@ManyToOne
	@JoinColumn(name = "authority_id")
	private @NonNull AuthorityEntity authority;
}
