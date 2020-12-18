package com.example.demo.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.dto.TaskDTO;
import com.example.demo.persistence.domain.Task;
import com.example.demo.service.TaskService;



@SpringBootTest // spring boot test lets spring know this is a test file and treat as such
@ActiveProfiles("dev") // lets us set the application porperties file.
public class TaskControllerUnitTest {

	@Autowired
	private TaskController controller;

	@MockBean
	private TaskService service;

	@Autowired
	private ModelMapper mapper;

	// same thing from our service
	private TaskDTO maptoDto(Task task) {
		return this.mapper.map(task, TaskDTO.class);
	}

	// During our test we want give it some data to use
	private final Task TEST_TASK_1 = new Task(1L, "Egg", "Easy");
	private final Task TEST_TASK_2 = new Task(2L, "Milk", "Medium");
	private final Task TEST_TASK_3 = new Task(3L, "Flour", "Hard");

	// I also want to create a list of cars that i can use later
	private final List<Task> LISTOFCARS = List.of(TEST_TASK_1, TEST_TASK_2, TEST_TASK_3);

	// Create
	@Test
	void createTest() throws Exception {
		when(this.service.create(TEST_TASK_1)).thenReturn(this.maptoDto(TEST_TASK_1));
		assertThat(new ResponseEntity<TaskDTO>(this.maptoDto(TEST_TASK_1), HttpStatus.CREATED))
				.isEqualTo(this.controller.create(TEST_TASK_1));
		verify(this.service, atLeastOnce()).create(TEST_TASK_1);

	}

	// Read one
	@Test
	void readOneTest() throws Exception {
		when(this.service.readOne(TEST_TASK_1.getId())).thenReturn(this.maptoDto(TEST_TASK_1));
		assertThat(new ResponseEntity<TaskDTO>(this.maptoDto(TEST_TASK_1), HttpStatus.OK))
				.isEqualTo(this.controller.readOne(TEST_TASK_1.getId()));
		verify(this.service, atLeastOnce()).readOne(TEST_TASK_1.getId());
	}

	// Read all
	@Test
	void readAllTest() throws Exception {
		List<TaskDTO> dtos = LISTOFCARS.stream().map(this::maptoDto).collect(Collectors.toList());
		when(this.service.readAll()).thenReturn(dtos);
		assertThat(this.controller.read()).isEqualTo(new ResponseEntity<>(dtos, HttpStatus.OK));
		verify(this.service, atLeastOnce()).readAll();

	}

	// Update
	@Test
	void UpdateTest() throws Exception {
		when(this.service.update(this.maptoDto(TEST_TASK_1), TEST_TASK_1.getId())).thenReturn(this.maptoDto(TEST_TASK_1));
		assertThat(new ResponseEntity<TaskDTO>(this.maptoDto(TEST_TASK_1), HttpStatus.ACCEPTED))
				.isEqualTo(this.controller.update(TEST_TASK_1.getId(), this.maptoDto(TEST_TASK_1)));
		verify(this.service, atLeastOnce()).update(this.maptoDto(TEST_TASK_1), TEST_TASK_1.getId());
	}

	// Delete
	@Test
	void deleteTest() throws Exception {
		when(this.service.delete(TEST_TASK_1.getId())).thenReturn(false);
		assertThat(this.controller.delete(TEST_TASK_1.getId()))
				.isEqualTo(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
		verify(this.service, atLeastOnce()).delete(TEST_TASK_1.getId());

	}

	@Test
	void deleteTest2() throws Exception {
		when(this.service.delete(TEST_TASK_1.getId())).thenReturn(true);
		assertThat(this.controller.delete(TEST_TASK_1.getId())).isEqualTo(new ResponseEntity<>(HttpStatus.NO_CONTENT));
		verify(this.service, atLeastOnce()).delete(TEST_TASK_1.getId());

	}

	// Find by name
	@Test
	void findByName() throws Exception {
		List<TaskDTO> dtos = LISTOFCARS.stream().map(this::maptoDto).collect(Collectors.toList());
		when(this.service.findByName(TEST_TASK_1.getName())).thenReturn(dtos);
		assertThat(this.controller.findByName(TEST_TASK_1.getName()))
				.isEqualTo(new ResponseEntity<List<TaskDTO>>(dtos, HttpStatus.OK));
		verify(this.service, atLeastOnce()).findByName(TEST_TASK_1.getName());
	}

}