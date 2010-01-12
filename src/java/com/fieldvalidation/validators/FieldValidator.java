package com.fieldvalidation.validators;

import java.text.ParseException;

import org.apache.log4j.Logger;
import com.fieldvalidation.constants.ErrorMessages;
import com.fieldvalidation.exception.InvalidFieldLengthException;
import com.fieldvalidation.exception.InvalidFieldValueException;

public class FieldValidator {

	public static String FIELDTYPE_USERID = "USERID";
	public static String FIELDTYPE_EMAILID = "EMAILID";
	public static String FIELDTYPE_DOB = "DOB";
	public static String FIELDTYPE_NAME = "NAME";
	public static String FIELDTYPE_MOBILENUMBER = "MOBILENUMBER";
	public static String FIELDTYPE_PINNUMBER = "PINNUMBER";
	public static String FIELDTYPE_PASSWORD = "PASSWORD";
	
	public static String FIELDNAME_FIRSTNAME="FIRSTNAME";
	public static String FIELDNAME_LASTNAME="LASTNAME";
	public static String FIELDNAME_EMAILID="EMAILID";
	public static String FIELDNAME_USERID="USERID";
	public static String FIELDNAME_MOBILENUMBER="MOBILENUMBER";
	public static String FIELDNAME_DOB="DOB";
	public static String FIELDNAME_PINNUMBER = "PINNUMBER";
	public static String FIELDNAME_PASSWORD = "PASSWORD";
	
	public static String errorMsg = null;

	public static boolean isValidField(String fieldName, String fieldType, String fieldValue, boolean isMandatory){

		final Logger mylogger = Logger.getLogger(FieldValidator.class);
		
		if(isMandatory && fieldValue==null){
			errorMsg = ErrorMessages.ERROR_MESSAGE_NULLFIELDVALUE;
			mylogger.error("Null value provided for " + fieldType + ".");
			return false;
		}
		
		if (isMandatory) {
			if (fieldValue!=null && fieldValue.trim().length() == 0) {
				errorMsg = ErrorMessages.ERROR_MESSAGE_BLANKFIELD;
				mylogger.error("No value provided for mandatory field "+fieldType+".");
				return false;
			}
		}

		if (fieldType.equals(FieldValidator.FIELDTYPE_EMAILID)) {
			try{
				EmailIdValidator.validateEmailId(fieldValue);
			
			}catch(InvalidFieldValueException e){
				
				errorMsg = ErrorMessages.ERROR_MESSAGE_INVALIDEMAILID;
				mylogger.error(e.getMessage());
				return false;
				
			}catch(InvalidFieldLengthException e){
				
				errorMsg = ErrorMessages.ERROR_MESSAGE_INVALIDEMAILID_INCORRECTLENGTH;
				mylogger.error(e.getMessage());
				return false;
			}
			
		}
		
		if (fieldType.equals(FieldValidator.FIELDTYPE_USERID)) {
			
			try{
				UserIdValidator.validateUserId(fieldValue);
			}catch(InvalidFieldValueException e){
				
				errorMsg = ErrorMessages.ERROR_MESSAGE_INVALIDUSERID;
				mylogger.error(e.getMessage());
				return false;
				
			}catch(InvalidFieldLengthException e){
				
				errorMsg = ErrorMessages.ERROR_MESSAGE_INVALIDUSERID_INCORRECTLENGTH;
				mylogger.error(e.getMessage());
				return false;
			}
			
		}
		
		if (fieldType.equals(FieldValidator.FIELDTYPE_DOB)) {
			try{
				DOBValidator.validateDOB(fieldValue);
			}catch(ParseException e){
				
				errorMsg = ErrorMessages.ERROR_MESSAGE_INVALIDDOB;
				mylogger.error(e.getMessage());
				return false;
			}
		}
		
		if (fieldType.equals(FieldValidator.FIELDTYPE_NAME)) {
			try{
				NameValidator.validateName(fieldValue);
			}catch(InvalidFieldLengthException e){
				
				if(fieldName.equals(FieldValidator.FIELDNAME_FIRSTNAME))
					errorMsg = ErrorMessages.ERROR_MESSAGE_INVALIDFIRSTNAME_INCORRECTLENGTH;
				
				if(fieldName.equals(FieldValidator.FIELDNAME_LASTNAME))
					errorMsg = ErrorMessages.ERROR_MESSAGE_INVALIDLASTNAME_INCORRECTLENGTH;
				
				mylogger.error(e.getMessage());
				return false;
			}catch(InvalidFieldValueException e){
				
				if(fieldName.equals(FieldValidator.FIELDNAME_FIRSTNAME))
					errorMsg = ErrorMessages.ERROR_MESSAGE_INVALIDFIRSTNAME;
				
				if(fieldName.equals(FieldValidator.FIELDNAME_LASTNAME))
					errorMsg = ErrorMessages.ERROR_MESSAGE_INVALIDLASTNAME;
				
				mylogger.error(e.getMessage());
				return false;
			}
		}
		

		if (fieldType.equals(FieldValidator.FIELDTYPE_MOBILENUMBER)) {
			try{
				NumberValidator.validateMobileNumber(fieldValue);
			}catch(InvalidFieldLengthException e){
				
				if(fieldName.equals(FieldValidator.FIELDNAME_MOBILENUMBER))
					errorMsg = ErrorMessages.ERROR_MESSAGE_INVALIDMOBILENUMBER_INCORRECTLENGTH;
				mylogger.error(e.getMessage());
				return false;
			}catch(InvalidFieldValueException e){
				
				if(fieldName.equals(FieldValidator.FIELDNAME_MOBILENUMBER))
					errorMsg = ErrorMessages.ERROR_MESSAGE_INVALIDMOBILENUMBER;
				
				mylogger.error(e.getMessage());
				return false;
			}
		}
		
		if (fieldType.equals(FieldValidator.FIELDTYPE_PINNUMBER)) {
			try{
				NumberValidator.validatePinNumber(fieldValue);
			}catch(InvalidFieldLengthException e){
				
				if(fieldName.equals(FieldValidator.FIELDNAME_PINNUMBER))
					errorMsg = ErrorMessages.ERROR_MESSAGE_INVALIDPINNUMBER_INCORRECTLENGTH;
				mylogger.error(e.getMessage());
				return false;
			}catch(InvalidFieldValueException e){
				
				if(fieldName.equals(FieldValidator.FIELDNAME_PINNUMBER))
					errorMsg = ErrorMessages.ERROR_MESSAGE_INVALIDPINNUMBER;
				mylogger.error(e.getMessage());
				return false;
			}
		}
		
		if (fieldType.equals(FieldValidator.FIELDTYPE_PASSWORD)) {
			try{
				PasswordValidator.validatePassword(fieldValue);
			}catch(InvalidFieldLengthException e){
				
				if(fieldName.equals(FieldValidator.FIELDNAME_PASSWORD))
					errorMsg = ErrorMessages.ERROR_MESSAGE_INVALIDPASSWORD_INCORRECTLENGTH;
				mylogger.error(e.getMessage());
				return false;
			}
		}
		return true;
	}
	
	
	
}
