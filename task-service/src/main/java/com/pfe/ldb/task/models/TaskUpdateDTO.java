package com.pfe.ldb.task.models;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskUpdateDTO {

	@ApiModelProperty(position = 0, required = true)
	private Integer id;
	
	@ApiModelProperty(position = 1, required = true)
	private @NotNull String name;
	
	@ApiModelProperty(position = 2, required = true)
	private @NotNull String description;
	
	@ApiModelProperty(position = 3, required = true)
	private @NotNull Integer taskGroupId;
}
