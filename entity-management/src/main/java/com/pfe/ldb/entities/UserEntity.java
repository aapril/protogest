package com.pfe.ldb.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class UserEntity extends AbstractEntity {

	@OneToOne
	@JoinColumn(name = "memberId")
	private MemberEntity member;
	private String username;
	private String password;
	
	public UserEntity() {
		
	}
	public UserEntity(MemberEntity member, String username, String password) {
		super();
		this.member = member;
		this.username = username;
		this.password = password;
	}
	public MemberEntity getMember() {
		return member;
	}
	public void setMember(MemberEntity member) {
		this.member = member;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
