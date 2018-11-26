package com.pfe.ldb.task.models;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskGroupDTO {

	@ApiModelProperty(position = 0)
	private Integer id;
	
	@ApiModelProperty(position = 1)
	private @NotNull String name;
	
	@ApiModelProperty(position = 2)
	private @NotNull String description;
	
	@ApiModelProperty(position = 3)
	private @NotNull Integer eventId;
}
