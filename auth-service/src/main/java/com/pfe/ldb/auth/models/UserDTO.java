package com.pfe.ldb.auth.models;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {

	@ApiModelProperty(position = 0)
	private @NotNull Integer id;

	@ApiModelProperty(position = 1)
	private @NotNull String username;
}
