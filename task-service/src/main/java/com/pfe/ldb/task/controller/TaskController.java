package com.pfe.ldb.task.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.ldb.entity.TaskEntity;
import com.pfe.ldb.entity.TaskGroupEntity;
import com.pfe.ldb.task.dto.TaskDTO;
import com.pfe.ldb.task.dto.TaskGroupDTO;
import com.pfe.ldb.task.repository.TaskGroupRepository;
import com.pfe.ldb.task.repository.TaskRepository;

@RestController
public class TaskController {

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private TaskGroupRepository taskGroupRepository;
	
	@Autowired
	private  ModelMapper modelMapper;
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/tasks")
    @ResponseBody
    public List<TaskDTO> getTasks() throws Exception {
		Iterable<TaskEntity> tasks = taskRepository.findAll();
        
		List<TaskDTO> dtos = new ArrayList<TaskDTO>();
		for(TaskEntity task : tasks) {
			dtos.add(modelMapper.map(task,  TaskDTO.class));
		}
		
		return dtos;
    }


	@RequestMapping(method = RequestMethod.GET, value = "/taskGroups")
    @ResponseBody
    public List<TaskGroupDTO> getTaskGroups() throws Exception {
		Iterable<TaskGroupEntity> taskGroups = taskGroupRepository.findAll();
        
		List<TaskGroupDTO> dtos = new ArrayList<TaskGroupDTO>();
		for(TaskGroupEntity taskGroup : taskGroups) {
			dtos.add(modelMapper.map(taskGroup,  TaskGroupDTO.class));
		}
		
		return dtos;
    }
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/task/{groupId}")
	@ResponseBody
    public List<TaskDTO> getTasksByGroup(@PathVariable(value = "groupId") Integer taskGroupId) throws Exception {
		Iterable<TaskEntity> tasks = taskRepository.findByTaskGroupId(taskGroupId);
        
		List<TaskDTO> dtos = new ArrayList<TaskDTO>();
		for(TaskEntity task : tasks) {
			dtos.add(modelMapper.map(task,  TaskDTO.class));
		}
		
		return dtos;
    }
	
}
