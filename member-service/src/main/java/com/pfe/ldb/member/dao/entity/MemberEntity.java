package com.pfe.ldb.member.dao.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Table(name = "member")
public class MemberEntity extends AbstractEntity {

	@Column(name = "firstName")
	private @NonNull String firstName;

	@Column(name = "lastName")
	private @NonNull String lastName;

	@Column(name = "email")
	private @NonNull String email;

	@OneToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;
}
