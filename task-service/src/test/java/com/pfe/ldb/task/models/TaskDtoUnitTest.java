package com.pfe.ldb.task.models;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.modelmapper.ModelMapper;

import com.pfe.ldb.entities.TaskEntity;
import com.pfe.ldb.entities.TaskGroupEntity;
import com.pfe.ldb.task.models.TaskDTO;

public class TaskDtoUnitTest {

	private final static Integer TASK_GROUP_ID = 3;
	private final static Integer TASK_ID = 5;
	private final static String TASK_NAME = "TASK_NAME";
	private final static String TASK_DESC = "TASK_DESC";
	
	private TaskEntity taskMock = mock(TaskEntity.class);
	private TaskGroupEntity taskGroupMock = mock(TaskGroupEntity.class);
	
	private ModelMapper modelMapper = new ModelMapper();
	
	@Test
	public void whenConvertTaskEntityToTaskDto_thenCorrect() {
		
		when(taskMock.getId()).thenReturn(TASK_ID);
		when(taskMock.getName()).thenReturn(TASK_NAME);
		when(taskMock.getDescription()).thenReturn(TASK_DESC);
		when(taskMock.getTaskGroup()).thenReturn(taskGroupMock);
		when(taskGroupMock.getId()).thenReturn(TASK_GROUP_ID);
		
		final TaskDTO taskDto = modelMapper.map(taskMock, TaskDTO.class);
		assertEquals(taskMock.getName(), taskDto.getName());
		assertEquals(taskMock.getDescription(), taskDto.getDescription());
		assertEquals(taskMock.getTaskGroup().getId(), taskDto.getTaskGroupId());
	}
	
	
	@Test
	public void whenConvertTaskDtoToTaskEntity_thenCorrect() {
		
		final TaskDTO taskDto = new TaskDTO();
		taskDto.setName(TASK_NAME);
		taskDto.setDescription(TASK_DESC);
		
		final TaskEntity task = modelMapper.map(taskDto,  TaskEntity.class);
		assertEquals(task.getName(), taskDto.getName());
		assertEquals(task.getDescription(), taskDto.getDescription());
	}
}
