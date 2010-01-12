package com.fieldvalidation.validators;

import com.fieldvalidation.exception.InvalidFieldLengthException;
import com.fieldvalidation.exception.InvalidFieldValueException;

public class EmailIdValidator {
     
	private static String emailRegex=".+@.+\\.[a-z]+";
	private static int emailLength=100;
	
	public static void validateEmailId(String fieldValue) throws InvalidFieldValueException,
	     											InvalidFieldLengthException{
		
		if(!isValidEmailId(fieldValue)){
			throw new InvalidFieldValueException(FieldValidator.FIELDNAME_EMAILID);
		}
		
		if(!hasValidLength(fieldValue)){
			throw new InvalidFieldLengthException(FieldValidator.FIELDNAME_EMAILID);
		}
	}
	
	private static boolean isValidEmailId(String fieldValue){
		
		if(fieldValue.matches(emailRegex))
			return true;
		
		return false;
	}

	private static boolean hasValidLength(String fieldValue){
		
		if(fieldValue.trim().length()<=emailLength)
			return true;
		
		return false;
	}
	
	
}
