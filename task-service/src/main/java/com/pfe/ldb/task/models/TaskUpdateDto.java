package com.pfe.ldb.task.models;

import lombok.NonNull;

public class TaskUpdateDto {

	private @NonNull Integer id;
	private String name;
	private String description;
	private Integer taskGroupId;
}
