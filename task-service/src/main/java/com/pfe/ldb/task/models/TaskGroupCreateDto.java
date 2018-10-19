package com.pfe.ldb.task.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class TaskGroupCreateDto {

	private @NonNull String name;
	private @NonNull String description;
	private Integer ParentGroupId;
}
