package com.timesgroup.sso.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.timesgroup.sso.utils.ShutDownHook;
import com.timesgroup.sso.utils.TimesMail;
import com.timesgroup.sso.utils.UserActivityLogger;

public class SSOStartupServlet extends HttpServlet {

	public void init() throws ServletException {

		TimesMail timesMail = TimesMail.getInstance();
		new Thread(timesMail).start();
		System.out.println("Mail Sender Daemon Started!!!");

		UserActivityLogger userActivityLogger = UserActivityLogger.getInstance();
		new Thread(userActivityLogger).start();
		System.out.println("User Activity Daemon Started!!!");

		ShutDownHook shutDownHook = new ShutDownHook();
		new Thread(shutDownHook).start();
		System.out.println("Hook to UserActivity Started!!!");
		Runtime.getRuntime().addShutdownHook(shutDownHook);

	}
}
