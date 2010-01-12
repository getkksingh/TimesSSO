package com.fieldvalidation.validators;

import com.fieldvalidation.exception.InvalidFieldLengthException;
import com.fieldvalidation.exception.InvalidFieldValueException;

public class NameValidator {

	private static String nameRegex = "[a-zA-Z]*";
	private static int nameLength = 50;

	public static void validateName(String fieldValue)
			throws InvalidFieldValueException, InvalidFieldLengthException {

		if (!isValidName(fieldValue)) {
			throw new InvalidFieldValueException(FieldValidator.FIELDNAME_FIRSTNAME);
		}

		if (!hasValidLength(fieldValue)) {
			throw new InvalidFieldLengthException(FieldValidator.FIELDNAME_FIRSTNAME);
		}
	}

	private static boolean isValidName(String fieldValue) {

		if (fieldValue.matches(nameRegex))
			return true;

		return false;
	}

	private static boolean hasValidLength(String fieldValue) {

		if (fieldValue.trim().length() <= nameLength)
			return true;

		return false;
	}
}
