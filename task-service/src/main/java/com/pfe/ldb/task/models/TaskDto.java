package com.pfe.ldb.task.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor 
@RequiredArgsConstructor 
public class TaskDto {
	
	private @NonNull Integer id;
	private @NonNull String name;
	private @NonNull String description;
	private @NonNull Integer taskGroupId;
}
