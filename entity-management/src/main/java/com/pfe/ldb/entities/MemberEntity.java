package com.pfe.ldb.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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

	private @NonNull String firstName;
	private @NonNull String lastName;
	private @NonNull String email;
	
	@OneToMany(mappedBy = "member")
	private List<EventEntity> events;
	
	@OneToOne
	private UserEntity user;
}
