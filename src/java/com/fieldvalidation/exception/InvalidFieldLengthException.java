package com.fieldvalidation.exception;

public class InvalidFieldLengthException extends Exception {

	public InvalidFieldLengthException(String fieldName) {
		
		super("Invalid "+fieldName+" Length Exception.");
	}
}
