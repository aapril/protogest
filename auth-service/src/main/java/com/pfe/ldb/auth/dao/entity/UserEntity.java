package com.pfe.ldb.auth.dao.entity;

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
@Table(name = "user")
public class UserEntity extends AbstractEntity {

	@Column(name = "username")
	private @NonNull String username;

	@Column(name = "password")
	private @NonNull String password;
}
