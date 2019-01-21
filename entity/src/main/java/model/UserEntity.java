package model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "user")
public class UserEntity extends AbstractEntity {

	@Column(name = "username")
	private @NonNull String username;

	@Column(name = "password")
	private @NonNull String password;

	@OneToOne(mappedBy = "user")
	private MemberEntity member;
}
