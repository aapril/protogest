package com.pfe.ldb.task.models;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskGroupDTO {

	@ApiModelProperty(notes = "Only for output purposes.")
	private Integer id;
	
	@ApiModelProperty(required = true)
	private @NotNull String name;
	
	@ApiModelProperty(required = true)
	private @NotNull String description;
	
	@ApiModelProperty(required = true)
	private Integer ParentGroupId;
}
