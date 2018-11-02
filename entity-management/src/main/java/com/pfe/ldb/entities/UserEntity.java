package com.pfe.ldb.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name="user")
public class UserEntity extends AbstractEntity {

	private @NonNull String username;
	private @NonNull String email;
	private @NonNull String password;	

	@OneToOne
	@JoinColumn(name = "memberId")
	private @NonNull MemberEntity member;
}
