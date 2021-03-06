package com.example.demo.persistence.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Todo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String name;
	
	@NotNull
	private String description;
	
	@OneToMany(mappedBy = "todo", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Task> task;
	
	public Todo(Long id, String name, String description, List<Task> task)
	{
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.task = task;
	}
	
	public Todo(String name, String description, List<Task> task)
	{
		super();
		this.name = name;
		this.description = description;
		this.task = task;
	}
}
