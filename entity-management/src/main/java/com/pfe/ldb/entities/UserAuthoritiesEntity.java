package com.pfe.ldb.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="userAuthorities")
public class UserAuthoritiesEntity extends AbstractEntity {

	@ManyToOne
	@JoinColumn(name = "userId")
	private UserEntity user;

	@ManyToOne
	@JoinColumn(name = "authorityId")
	private AuthoritiesEntity authority;
	
	
	public UserAuthoritiesEntity() {
		
	}
	
	public UserAuthoritiesEntity(UserEntity user, AuthoritiesEntity authority) {
		super();
		this.user = user;
		this.authority = authority;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public AuthoritiesEntity getAuthority() {
		return authority;
	}

	public void setAuthority(AuthoritiesEntity authority) {
		this.authority = authority;
	}
}
