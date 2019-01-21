package model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "eventState")
public class EventStateEntity extends AbstractEntity {

	@Column(name = "name")
	private @NonNull String name;
	
	@OneToMany(mappedBy = "eventState")
	private List<EventEntity> events;
}
