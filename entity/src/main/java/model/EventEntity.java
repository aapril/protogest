package model;

import lombok.*;
import model.AbstractEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "event")
public class EventEntity extends AbstractEntity {

	
	@Column(name = "name")
	private @NonNull String name;
	
	@Column(name = "description")
	private @NonNull String description;
	
	@Column(name = "event_date")
	private @NonNull Date eventDate;
	
	@Column(name = "event_group_id")
	private @NonNull Integer eventGroupId;

	@Column(name = "member_id")
	private @NonNull Integer memberId;

	@Column(name = "event_state_id")
	private @NonNull Integer eventStateId;

	@OneToMany(mappedBy = "event")
	private List<TaskGroupEntity> taskGroups;

	@ManyToOne
	@JoinColumn(name = "event_group_id")
	private @NonNull EventGroupEntity eventGroup;

	@ManyToOne
	@JoinColumn(name = "event_state_id")
	private @NonNull EventStateEntity eventState;
}
