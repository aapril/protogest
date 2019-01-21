package model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "vote")
public class VoteEntity extends AbstractEntity {

	@Column(name = "name")
	private @NonNull String name;
	
	@Column(name = "description")
	private @NonNull String description;

	@ManyToOne
	@JoinColumn(name = "vote_state_id")
	private @NonNull VoteStateEntity voteState;
}
