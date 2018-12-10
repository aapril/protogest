package com.pfe.ldb.member.dto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberDTO {

	@ApiModelProperty(position = 0)
	private Integer id;
	
	@ApiModelProperty(position = 1)
	private @NotNull String firstName;
	
	@ApiModelProperty(position = 2)
	private @NotNull String lastName;
	
	@ApiModelProperty(position = 3)
	private @NotNull String email;
	
	@ApiModelProperty(position = 4)
	private @NotNull Integer userId;
}
