package com.pfe.ldb.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.ManyToMany;
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
@Table(name = "user")
public class UserEntity extends AbstractEntity {

	@JoinColumn(name = "username")
	private @NonNull String username;

	@JoinColumn(name = "password")
	private @NonNull String password;

	@OneToOne(mappedBy = "user")
	private MemberEntity member;

	@ManyToMany
	private List<AuthorityEntity> authorities;
}
