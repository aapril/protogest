package com.pfe.ldb.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="authorities")
public class AuthoritiesEntity extends AbstractEntity {

	private String name;
	
	public AuthoritiesEntity() {
		
	}
	
	public AuthoritiesEntity(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
