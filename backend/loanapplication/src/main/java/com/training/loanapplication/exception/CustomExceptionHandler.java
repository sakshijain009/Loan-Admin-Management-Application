package com.training.loanapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value=DuplicateEntryException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public @ResponseBody ErrorResponse handleDuplicateEntryException(DuplicateEntryException ex)
	{
		return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
	}
	
	@ExceptionHandler(value=ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public @ResponseBody ErrorResponse handleResourceNotFoundException(ResourceNotFoundException ex)
	{
		return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	}
	
	@ExceptionHandler(value=AuthenticationFailedException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorResponse handleAuthenticationFailedException(AuthenticationFailedException ex)
	{
		return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
	}
}
