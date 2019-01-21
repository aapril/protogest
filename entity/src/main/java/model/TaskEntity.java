package model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "task")
public class TaskEntity extends AbstractEntity {

	@Column(name = "name")
	private @NonNull String name;

	@Column(name = "description")
	private @NonNull String description;

	@ManyToOne
	@JoinColumn(name = "task_group_id")
	private @NonNull TaskGroupEntity taskGroup;
}
