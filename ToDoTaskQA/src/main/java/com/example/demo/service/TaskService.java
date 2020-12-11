package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.TaskDTO;
import com.example.demo.exception.TaskNotFoundException;
import com.example.demo.persistence.domain.Task;
import com.example.demo.persistence.repo.TaskRepo;
import com.example.demo.util.SpringBeanUtil;

@Service
public class TaskService {
	
	private TaskRepo repo;

	private ModelMapper mapper;

	private TaskDTO mapToDTO(Task task)
	{
		return this.mapper.map(task, TaskDTO.class);
	}
	
	@Autowired
	public TaskService(TaskRepo repo, ModelMapper mapper)
	{
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	// Create
	public TaskDTO create(Task task) {
		return this.mapToDTO(this.repo.save(task));
	}
	
	//Read all
	public List<TaskDTO> readAll()
	{
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
	//Read One
	public TaskDTO readOne(Long id)
	{
		return this.mapToDTO(this.repo.findById(id).orElseThrow(TaskNotFoundException::new));
	}
	
	//Update
	public TaskDTO update(TaskDTO taskDTO, Long id)
	{
		// check if record exists
		Task toUpdate = this.repo.findById(id).orElseThrow(TaskNotFoundException::new);
		// set the record to update
		toUpdate.setName(taskDTO.getName());
		// check update for any nulls
		SpringBeanUtil.mergeNotNull(taskDTO, toUpdate);
		// retun the method from repo
		return this.mapToDTO(this.repo.save(toUpdate));
	}
	
	// Delete
	public boolean delete(Long id) {
		this.repo.deleteById(id);// true
		return !this.repo.existsById(id);// true
	}
	
	public List<TaskDTO> findByName(String name) {
		return this.repo.findByName(name).stream().map(this::mapToDTO).collect(Collectors.toList());
		// stream - returns a sequential stream considering collection as its source
		// map - used to map each element to its corresponding result
		// :: - for each e.g. Random random = new Random();
		// random.ints().limit(10).forEach(System.out::println);
		// Collectors - used to return a list or string
	}
}
