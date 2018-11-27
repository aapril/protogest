package com.pfe.ldb.auth.dao.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import org.springframework.security.core.GrantedAuthority;

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
@Table(name = "authorities")
public class AuthorityEntity extends AbstractEntity implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@JoinColumn(name = "name")
	private @NonNull String authority;
}