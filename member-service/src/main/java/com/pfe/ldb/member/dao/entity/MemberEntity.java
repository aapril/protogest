package com.pfe.ldb.member.dao.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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

	@JoinColumn(name = "firstName")
	private @NonNull String firstName;

	@JoinColumn(name = "lastName")
	private @NonNull String lastName;

	@JoinColumn(name = "email")
	private @NonNull String email;

	@JoinColumn(name = "user_id")
	private Integer userId;
}
