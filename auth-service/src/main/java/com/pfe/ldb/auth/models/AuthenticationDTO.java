package com.pfe.ldb.auth.models;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthenticationDTO {

	@ApiModelProperty(notes = "Only for output purposes.")
	private @NotNull Integer id;
	
	@ApiModelProperty(required = true)
	private @NotNull String username;

	@ApiModelProperty(required = true)
	private @NotNull String email;

	@ApiModelProperty(required = true)
	private @NotNull List<GrantedAuthority> roles;
}
