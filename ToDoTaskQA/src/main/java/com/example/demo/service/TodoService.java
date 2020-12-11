package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.TodoDTO;
import com.example.demo.exception.TodoNotFoundException;
import com.example.demo.persistence.domain.Todo;
import com.example.demo.persistence.repo.TodoRepo;
import com.example.demo.util.SpringBeanUtil;

@Service
public class TodoService {
	
	private TodoRepo repo;

	private ModelMapper mapper;

	private TodoDTO mapToDTO(Todo todo)
	{
		return this.mapper.map(todo, TodoDTO.class);
	}
	
	@Autowired
	public TodoService(TodoRepo repo, ModelMapper mapper)
	{
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	// Create
	public TodoDTO create(Todo todo) {
		return this.mapToDTO(this.repo.save(todo));
	}
	
	//Read all
	public List<TodoDTO> readAll()
	{
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
	//Read One
	public TodoDTO readOne(Long id)
	{
		return this.mapToDTO(this.repo.findById(id).orElseThrow(TodoNotFoundException::new));
	}
	
	//Update
	public TodoDTO update(TodoDTO todoDTO, Long id)
	{
		// check if record exists
		Todo toUpdate = this.repo.findById(id).orElseThrow(TodoNotFoundException::new);
		// set the record to update
		toUpdate.setName(todoDTO.getName());
		// check update for any nulls
		SpringBeanUtil.mergeNotNull(todoDTO, toUpdate);
		// retun the method from repo
		return this.mapToDTO(this.repo.save(toUpdate));
	}
	
	// Delete
	public boolean delete(Long id) {
		this.repo.deleteById(id);// true
		return !this.repo.existsById(id);// true
	}
	
	public List<TodoDTO> findByName(String name) {
		return this.repo.findByName(name).stream().map(this::mapToDTO).collect(Collectors.toList());
		// stream - returns a sequential stream considering collection as its source
		// map - used to map each element to its corresponding result
		// :: - for each e.g. Random random = new Random();
		// random.ints().limit(10).forEach(System.out::println);
		// Collectors - used to return a list or string
	}
}
