package com.fieldvalidation.validators;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DOBValidator {

	public static boolean validateDOB(String fieldValue) throws ParseException {

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date parsedDate = df.parse(fieldValue);
        return true;
	}

}
