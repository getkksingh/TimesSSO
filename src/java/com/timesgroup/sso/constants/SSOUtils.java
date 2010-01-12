package com.timesgroup.sso.constants;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

public class SSOUtils {

	public static PrintWriter getPrintWriter(HttpServletResponse response) {

		PrintWriter responseWriter = null;

		try {
			responseWriter = response.getWriter();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return responseWriter;
	}
	
	public static Date getDate(String date){
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date parsedDate=null;
		try{
		     parsedDate = df.parse("23/09/1987");           
		     
		} catch (ParseException e)
		{
		     e.printStackTrace();
		}
		
		return parsedDate;
	}
	
	public static String getUUID(){
		
		
		return "abcdefg";
	}

}
