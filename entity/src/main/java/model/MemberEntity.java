package model;

import lombok.*;

import javax.persistence.*;

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
