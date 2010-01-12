package com.fieldvalidation.exception;

public class InvalidFieldValueException extends Exception {

	public InvalidFieldValueException(String fieldName) {
		
		super("Invalid "+fieldName+" Exception.");
	}
}
