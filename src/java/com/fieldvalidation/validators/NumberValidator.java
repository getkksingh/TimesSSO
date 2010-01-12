package com.fieldvalidation.validators;

import com.fieldvalidation.exception.InvalidFieldLengthException;
import com.fieldvalidation.exception.InvalidFieldValueException;

public class NumberValidator {
	
	private static String numberRegex="^[0-9]*$";
	private static int mobileNumberLength = 10;
	private static int pinNumberLength = 6;
	
	public static void validateMobileNumber(String fieldValue) throws InvalidFieldValueException,
	     											InvalidFieldLengthException{
		
		if(!hasValidLength(FieldValidator.FIELDNAME_MOBILENUMBER, fieldValue)){
			throw new InvalidFieldLengthException(FieldValidator.FIELDNAME_MOBILENUMBER);
		}
		
		if(!isValidNumber(fieldValue)){
			throw new InvalidFieldValueException(FieldValidator.FIELDNAME_MOBILENUMBER);
		}
	}
	
	public static void validatePinNumber(String fieldValue) throws InvalidFieldValueException,
		InvalidFieldLengthException{

		if(!hasValidLength(FieldValidator.FIELDNAME_PINNUMBER, fieldValue)){
			throw new InvalidFieldLengthException(FieldValidator.FIELDNAME_PINNUMBER);
		}
		
		if(!isValidNumber(fieldValue)){
			throw new InvalidFieldValueException(FieldValidator.FIELDNAME_PINNUMBER);
		}
	}
	
	private static boolean isValidNumber(String fieldValue){
		
		if(fieldValue.matches(numberRegex))
			return true;
		
		return false;
	}

	private static boolean hasValidLength(String fieldName, String fieldValue){
		
		int fieldLength=0;
		
		if(fieldName.equals(FieldValidator.FIELDNAME_MOBILENUMBER))
			fieldLength=mobileNumberLength;
		
		if(fieldName.equals(FieldValidator.FIELDNAME_PINNUMBER))
			fieldLength=pinNumberLength;
		
		if(fieldValue.trim().length()==fieldLength)
			return true;
		
		return false;
	}
}
