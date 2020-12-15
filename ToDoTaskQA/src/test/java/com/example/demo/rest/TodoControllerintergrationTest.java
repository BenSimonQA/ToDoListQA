package com.example.demo.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.example.demo.dto.TodoDTO;
import com.example.demo.persistence.domain.Task;
import com.example.demo.persistence.domain.Todo;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
// sql runs in order schema followed by data file - JH dont make the mistake
@Sql(scripts = { "classpath:task-schema.sql",
		"classpath:task-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "dev")
public class TodoControllerintergrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper jsonifier;

	@Autowired
	private ModelMapper mapper;

	private TodoDTO mapToDTO(Todo todo) {
		return this.mapper.map(todo, TodoDTO.class);
	}

	// During our test we want give it some data to use
	List<Task> task = new ArrayList<>();
	private final Todo TEST_todo_1 = new Todo("Egg","Easy", task);
	private final Todo TEST_todo_2 = new Todo("Milk","Medium", task);
	private final Todo TEST_todo_3 = new Todo("Flour","Hard", task);


	// I also want to create a list of cars that i can use later
	private final List<Todo> LISTOFTODO = List.of(TEST_todo_1, TEST_todo_2, TEST_todo_3);

	private final String URI = "/todo";

	// Create test
	@Test
	void createTest() throws Exception {
		List<Task> task = new ArrayList<>();
		TodoDTO testDTO = mapToDTO(new Todo("Water","Easy",task));
		String testDTOAsJSON = this.jsonifier.writeValueAsString(testDTO);

		RequestBuilder request = post(URI + "/create").contentType(MediaType.APPLICATION_JSON).content(testDTOAsJSON);

		ResultMatcher checkStatus = status().isCreated();

		TodoDTO testSavedDTO = mapToDTO(new Todo("Water", "Easy", task));
		testSavedDTO.setId(4L);
		String testSavedDTOAsJSON = this.jsonifier.writeValueAsString(testSavedDTO);

		ResultMatcher checkBody = content().json(testSavedDTOAsJSON);

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);

//		this.mvc.perform(post(URI + "/create").contentType(MediaType.APPLICATION_JSON).content(testDTOAsJSON))
//				.andExpect(status().isCreated()).andExpect(content().json(testSavedDTOAsJSON));
	}

	// Update test
	@Test
	void updateTest() throws Exception {
		List<Task> task = new ArrayList<>();
		TodoDTO testDTO = mapToDTO(new Todo("Gas", "Hard", task));
		String testDTOAsJSON = this.jsonifier.writeValueAsString(testDTO);

		RequestBuilder request = put(URI + "/update/1").contentType(MediaType.APPLICATION_JSON).content(testDTOAsJSON);

		ResultMatcher checkStatus = status().isAccepted();

		TodoDTO testSavedDTO = mapToDTO(new Todo("Gas", "Hard", task));
		testSavedDTO.setId(1L);
		String testSavedDTOAsJSON = this.jsonifier.writeValueAsString(testSavedDTO);

		ResultMatcher checkBody = content().json(testSavedDTOAsJSON);

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);

//		this.mvc.perform(post(URI + "/create").contentType(MediaType.APPLICATION_JSON).content(testDTOAsJSON))
//				.andExpect(status().isCreated()).andExpect(content().json(testSavedDTOAsJSON));
	}
	
	// Read test
	@Test
	void updateRead1() throws Exception {
		List<Task> task = new ArrayList<>();
		TodoDTO testDTO = mapToDTO(new Todo("Egg","Easy", task));
		String testDTOAsJSON = this.jsonifier.writeValueAsString(testDTO);

		RequestBuilder request = get(URI + "/read/1").contentType(MediaType.APPLICATION_JSON).content(testDTOAsJSON);

		ResultMatcher checkStatus = status().isOk();

		TodoDTO testSavedDTO = mapToDTO(new Todo("Egg","Easy", task));
		testSavedDTO.setId(1L);
		String testSavedDTOAsJSON = this.jsonifier.writeValueAsString(testSavedDTO);

		ResultMatcher checkBody = content().json(testSavedDTOAsJSON);

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);

//		this.mvc.perform(post(URI + "/create").contentType(MediaType.APPLICATION_JSON).content(testDTOAsJSON))
//				.andExpect(status().isCreated()).andExpect(content().json(testSavedDTOAsJSON));
	}
	
	// Read All test
	@Test
	void updateReadAll() throws Exception {
		TodoDTO testDTO = mapToDTO(new Todo("Egg","Easy", task));
		TodoDTO testDTO2 = mapToDTO(new Todo("Milk","Medium", task));
		TodoDTO testDTO3 = mapToDTO(new Todo("Flour", "Hard", task));
		List<TodoDTO> listDTO = new ArrayList<>();
		listDTO.add(testDTO);
		listDTO.add(testDTO2);
		listDTO.add(testDTO3);

		String testDTOAsJSON = this.jsonifier.writeValueAsString(listDTO);

		RequestBuilder request = get(URI + "/read").contentType(MediaType.APPLICATION_JSON).content(testDTOAsJSON);

		ResultMatcher checkStatus = status().isOk();

		TodoDTO testSavedDTO = mapToDTO(new Todo("Egg","Easy", task));
		TodoDTO testSavedDTO2 = mapToDTO(new Todo("Milk","Medium", task));
		TodoDTO testSavedDTO3 = mapToDTO(new Todo("Flour","Hard", task));
		List<TodoDTO> listSavedDTO = new ArrayList<>();
		testSavedDTO.setId(1L);
		testSavedDTO2.setId(2L);
		testSavedDTO3.setId(3L);
		listSavedDTO.add(testSavedDTO);
		listSavedDTO.add(testSavedDTO2);
		listSavedDTO.add(testSavedDTO3);
		String testSavedDTOAsJSON = this.jsonifier.writeValueAsString(listSavedDTO);

		ResultMatcher checkBody = content().json(testSavedDTOAsJSON);

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);

//		this.mvc.perform(post(URI + "/create").contentType(MediaType.APPLICATION_JSON).content(testDTOAsJSON))
//				.andExpect(status().isCreated()).andExpect(content().json(testSavedDTOAsJSON));
	}
	
	// Delete test
	@Test
	void deleteTest() throws Exception {

		RequestBuilder request = delete(URI + "/delete/1").contentType(MediaType.APPLICATION_JSON);

		ResultMatcher checkStatus = status().isNoContent();

		this.mvc.perform(request).andExpect(checkStatus);

//		this.mvc.perform(post(URI + "/create").contentType(MediaType.APPLICATION_JSON).content(testDTOAsJSON))
//				.andExpect(status().isCreated()).andExpect(content().json(testSavedDTOAsJSON));
	}
	
	
	// Read Name test
	@Test
	void testFindName() throws Exception {
		List<Task> task = new ArrayList<>();
		TodoDTO testDTO = mapToDTO(new Todo("Egg","Easy", task));
		String testDTOAsJSON = this.jsonifier.writeValueAsString(testDTO);
		List<TodoDTO> listDTO = new ArrayList<>();
		listDTO.add(testDTO);

		RequestBuilder request = get(URI + "/findByName/Egg").contentType(MediaType.APPLICATION_JSON).content(testDTOAsJSON);

		ResultMatcher checkStatus = status().isOk();

		TodoDTO testSavedDTO = mapToDTO(new Todo("Egg","Easy", task));
		testSavedDTO.setId(1L);
		List<TodoDTO> listSavedDTO = new ArrayList<>();
		listSavedDTO.add(testSavedDTO);
		String testSavedDTOAsJSON = this.jsonifier.writeValueAsString(listSavedDTO);

		ResultMatcher checkBody = content().json(testSavedDTOAsJSON);

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);

//		this.mvc.perform(post(URI + "/create").contentType(MediaType.APPLICATION_JSON).content(testDTOAsJSON))
//				.andExpect(status().isCreated()).andExpect(content().json(testSavedDTOAsJSON));
	}
}
