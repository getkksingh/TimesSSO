package com.fieldvalidation.exception;

public class NullFieldValueException extends Exception {

	public NullFieldValueException(String fieldName) {

		super("Null value provided for " + fieldName + ".");
	}
}
