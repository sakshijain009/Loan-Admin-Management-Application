package com.training.loanapplication.exception;

public class DuplicateEntryException extends Exception {
	public static final long serialVersionUID = 1L;
	public DuplicateEntryException(String message) {
		super(message);
	}
}
