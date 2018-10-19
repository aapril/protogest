package com.pfe.ldb.entities;

import javax.persistence.Entity;
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
@Table(name="member")
public class MemberEntity extends AbstractEntity {

	private @NonNull String firstName;
	private @NonNull String lastName;
	private @NonNull String email;	
}
