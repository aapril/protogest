package com.pfe.ldb.event.models;

import java.util.Date;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class EventDTO {

	@ApiModelProperty(notes = "Only for output purposes.")
	private Integer id;

	@ApiModelProperty(required = true)
	private @NotNull String name;

	@ApiModelProperty(required = true)
	private @NotNull String description;

	@ApiModelProperty(required = true)
	private @NotNull Date eventDate;

	@ApiModelProperty(required = true)
	private @NotNull Integer eventGroupId;

	@ApiModelProperty(required = true)
	private @NotNull Integer taskId;

	@ApiModelProperty(required = true)
	private @NotNull Integer authorId;

	@ApiModelProperty(required = true)
	private @NotNull Integer eventStateId;
}
