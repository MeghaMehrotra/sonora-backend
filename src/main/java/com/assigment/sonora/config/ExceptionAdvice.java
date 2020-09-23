package com.assigment.sonora.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.assignment.sonora.dto.ErrorDTO;
import com.assignment.sonora.dto.MessageType;

public class ExceptionAdvice {

	@ExceptionHandler(value = Exception.class)

	public ResponseEntity<ErrorDTO> handleGenricException(Exception e) {

		ErrorDTO error = new ErrorDTO();
		List<String> message = new ArrayList<String>();
		message.add(e.getMessage());
		error.setMessage(message);
		error.setType(MessageType.ERROR);
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
}
