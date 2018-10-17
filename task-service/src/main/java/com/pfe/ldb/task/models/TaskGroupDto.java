package com.pfe.ldb.task.models;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskGroupDto {

	private @NotNull Integer id;
	private @NotNull String name;
	private @NotNull String description;
	private @NotNull Integer ParentGroupId;
}
