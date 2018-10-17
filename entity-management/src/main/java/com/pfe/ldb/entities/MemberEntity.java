package com.pfe.ldb.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="member")
public class MemberEntity extends AbstractEntity {

	private String firstName;
	private String lastName;
	private String email;
	
	public MemberEntity() {
	}
	
	public MemberEntity(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
