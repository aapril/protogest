package com.pfe.ldb.event.dto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EventGroupDTO {

	@ApiModelProperty(position = 0)
	private Integer id;

	@ApiModelProperty(position = 1)
	private @NotNull String name;
	
	@ApiModelProperty(position = 2)
	private @NotNull String description;
}
