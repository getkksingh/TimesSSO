package com.fieldvalidation.exception;

public class BlankFieldValueException extends Exception {
	
	public BlankFieldValueException(String fieldName) {
		
		super("No value provided for mandatory field "+fieldName+".");
	}
}
