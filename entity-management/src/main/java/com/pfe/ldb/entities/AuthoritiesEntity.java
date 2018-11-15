package com.pfe.ldb.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

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
public class AuthoritiesEntity extends AbstractEntity implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	private @NonNull String name;

	@Override
	public String getAuthority() {
		return this.name;
	}
}
