package model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "taskGroup")
public class TaskGroupEntity extends AbstractEntity {

	@Column(name = "name")
	private @NonNull String name;

	@Column(name = "description")
	private @NonNull String description;

	@ManyToOne
	@JoinColumn(name = "event_id")
	private EventEntity event;

	@OneToMany(mappedBy = "taskGroup")
	private List<TaskEntity> tasks;
}
