package com.pfe.ldb.task.models;

import lombok.NonNull;

public class TaskGroupUpdateDto {

	private @NonNull Integer id;
	private String name;
	private String description;
	private Integer ParentGroupId;
}
