package com.pfe.ldb.task.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskGroupDTO {

	@NotNull
	private Integer id;
	
	@NotNull
	private String name;
	
	@NotNull
	private String description;
	
	private Integer ParentGroupId;
}
