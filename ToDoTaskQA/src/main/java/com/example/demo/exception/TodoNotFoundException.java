package com.example.demo.exception;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "To Do does not exist with that id try again")

public class TodoNotFoundException extends EntityNotFoundException{
	private static final long serialVersionUID = 1L;
}