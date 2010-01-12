package com.fieldvalidation.validators;

import com.fieldvalidation.exception.InvalidFieldLengthException;

public class PasswordValidator {
	
	private static int passwordMaxLength = 30;

	public static void validatePassword(String fieldValue)
			throws InvalidFieldLengthException {

		if (!hasValidLength(fieldValue)) {
			throw new InvalidFieldLengthException(FieldValidator.FIELDNAME_PASSWORD);
		}
	}

	private static boolean hasValidLength(String fieldValue) {

		if (fieldValue.trim().length() <= passwordMaxLength)
			return true;

		return false;
	}

}
