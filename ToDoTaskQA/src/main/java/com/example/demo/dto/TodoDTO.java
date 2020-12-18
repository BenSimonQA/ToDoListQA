package com.example.demo.dto;

import java.util.List;

import lombok.Data;

@Data
public class TodoDTO {
	private Long id;
	private String name;
	private String description;
	private List<TaskDTO> task;
}
