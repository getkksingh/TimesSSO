package com.fieldvalidation.validators;

import com.fieldvalidation.exception.InvalidFieldLengthException;
import com.fieldvalidation.exception.InvalidFieldValueException;

public class UserIdValidator {
	
	private static String userIdRegex="[a-z0-9_]*";
	private static int userIdLength=50;
	
	public static void validateUserId(String fieldValue) throws InvalidFieldValueException,
		InvalidFieldLengthException{

		if(!isValidUserId(fieldValue)){
			throw new InvalidFieldValueException(FieldValidator.FIELDNAME_USERID);
		}
		
		if(!hasValidLength(fieldValue)){
			throw new InvalidFieldLengthException(FieldValidator.FIELDNAME_USERID);
		}
	}
	
	private static boolean isValidUserId(String fieldValue){
		
		if(fieldValue.matches(userIdRegex))
			return true;
		
		return false;
	}
	
	private static boolean hasValidLength(String fieldValue){
		
		if(fieldValue.trim().length()<=userIdLength)
			return true;
		
		return false;
	}

}
