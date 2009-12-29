package com.timesgroup.sso.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.timesgroup.sso.constants.SSOConstants;
import com.timesgroup.sso.constants.TimesMail;

public class SSOStartupServlet extends HttpServlet {
	  
	public void init() throws ServletException {
		   
		  TimesMail timesMail = TimesMail.getInstance();
		  new Thread(timesMail).start();
		  System.out.println("Mail Sender Daemon Started!!!");
		  
	}
}

