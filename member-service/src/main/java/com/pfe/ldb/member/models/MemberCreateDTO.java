package com.pfe.ldb.member.models;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberCreateDTO {

	@ApiModelProperty(position = 0, required = true)
	private @NotNull String firstName;

	@ApiModelProperty(position = 1, required = true)
	private @NotNull String lastName;

	@ApiModelProperty(position = 2, required = true)
	private @NotNull String email;

	@ApiModelProperty(position = 3, required = true)
	private @NotNull Integer userId;
}
