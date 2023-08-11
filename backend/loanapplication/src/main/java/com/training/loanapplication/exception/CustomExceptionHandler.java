package com.training.loanapplication.exception;

<<<<<<< HEAD
=======

>>>>>>> 4d746a8e7d59deefb0a3046de303f558bb098670
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
<<<<<<< HEAD
=======
//import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
>>>>>>> 4d746a8e7d59deefb0a3046de303f558bb098670
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	
<<<<<<< HEAD
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		Map<String, Object> responseBody = new LinkedHashMap<>();
		
		responseBody.put("timestamp", new Date());
		responseBody.put("status", status.value());	
		
=======
	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		Map<String, Object> responseBody = new LinkedHashMap<>();
		responseBody.put("timestamp", new Date());
		responseBody.put("status", status.value());	
>>>>>>> 4d746a8e7d59deefb0a3046de303f558bb098670
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x->x.getDefaultMessage())
			.collect(Collectors.toList());
		
		responseBody.put("errors", errors);
		
		return new ResponseEntity<>(responseBody, headers, status);
	}
			
	
<<<<<<< HEAD
}
=======
}
>>>>>>> 4d746a8e7d59deefb0a3046de303f558bb098670
