package com.timesgroup.sso.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieTestServlet extends HttpServlet {

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		Cookie cookie = new Cookie("CookieName", "CookieValue");
		cookie.setDomain("krishna.indiatimes.com");
		response.addCookie(cookie);
		
	}
}
