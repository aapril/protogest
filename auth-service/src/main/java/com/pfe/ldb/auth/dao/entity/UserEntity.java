package com.pfe.ldb.auth.dao.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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

	@ManyToMany
	private List<AuthorityEntity> authorities;
}
