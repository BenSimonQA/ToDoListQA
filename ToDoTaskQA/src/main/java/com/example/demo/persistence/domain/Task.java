package com.example.demo.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // classes that represent tables in our DB
@Data
@NoArgsConstructor
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String name;
	
	@NotNull
	private String difficulty;
	
	@ManyToOne
	private Todo todo;
	
	public Task(Long id, String name, String difficulty)
	{
		super();
		this.id = id;
		this.name = name;
		this.difficulty = difficulty;
	}
	
	public Task(String name, String  difficulty)
	{
		super();
		this.name = name;
		this.difficulty = difficulty;

	}
}
