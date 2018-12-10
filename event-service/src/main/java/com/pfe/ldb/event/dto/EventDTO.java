package com.pfe.ldb.event.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EventDTO {

	@ApiModelProperty(position = 0)
	private Integer id;

	@ApiModelProperty(position = 1)
	private @NotNull String name;

	@ApiModelProperty(position = 2)
	private @NotNull String description;

	@ApiModelProperty(position = 3)
	private @NotNull Date eventDate;

	@ApiModelProperty(position = 4)
	private @NotNull Integer eventGroupId;

	@ApiModelProperty(position = 5)
	private @NotNull Integer memberId;

	@ApiModelProperty(position = 6)
	private @NotNull Integer eventStateId;
}
