package com.example.demo.rest;

import org.junit.jupiter.api.Test;

import com.example.demo.dto.TaskDTO;
import com.example.demo.dto.TodoDTO;

import nl.jqno.equalsverifier.EqualsVerifier;

public class CoverageTest {

	@Test
	void testTodoDTO() {
		EqualsVerifier.simple().forClass(TodoDTO.class).verify();
	}
	
	@Test
	void testTaskDTO() {
		EqualsVerifier.simple().forClass(TaskDTO.class).verify();
	}
}
