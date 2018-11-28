package com.pfe.ldb.event.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EventGroupUpdateDTO {

	@ApiModelProperty(position = 0, required = true)
	private @NotNull String name;

	@ApiModelProperty(position = 1, required = true)
	private @NotNull String description;
}
