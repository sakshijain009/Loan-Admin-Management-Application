package com.training.loanapplication.exception;

public class ResourceNotFoundException extends Exception{
	public static final long serialVersionUID = 1L;
	public ResourceNotFoundException(String message) {
		super(message);
	}
}
