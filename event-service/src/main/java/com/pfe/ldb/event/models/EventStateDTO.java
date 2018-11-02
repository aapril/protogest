package com.pfe.ldb.event.models;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EventStateDTO {

	@ApiModelProperty(position = 0, notes = "Only for output purposes.")
	private Integer id;

	@ApiModelProperty(position = 1, required = true)
	private @NotNull String name;	
}
