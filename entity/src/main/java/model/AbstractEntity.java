package model;

import lombok.Getter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Getter
@MappedSuperclass
public abstract class AbstractEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	protected Integer id;

	@Temporal(TemporalType.DATE)
	private final Date createdDate;

	public AbstractEntity() {
		this.createdDate = new Date(Calendar.getInstance().getTime().getTime());
	}
}
