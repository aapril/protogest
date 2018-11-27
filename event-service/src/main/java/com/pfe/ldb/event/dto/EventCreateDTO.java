package com.pfe.ldb.event.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EventCreateDTO {

	@ApiModelProperty(position = 0, required = true)
	private Integer id;

	@ApiModelProperty(position = 1, required = true)
	private @NotNull String name;

	@ApiModelProperty(position = 2, required = true)
	private @NotNull String description;

	@ApiModelProperty(position = 3, required = true)
	private @NotNull Date eventDate;

	@ApiModelProperty(position = 4, required = true)
	private @NotNull Integer eventGroupId;

	@ApiModelProperty(position = 5, required = true)
	private @NotNull Integer memberId;
}
