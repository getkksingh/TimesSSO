<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cookie Generator</title>
</head>
<body>
<%

   	String cookieName = "newCookie";
	Cookie cookies [] = request.getCookies();
	Cookie myCookie = null;
	if (cookies != null){
		for (int i = 0; i < cookies.length; i++){
			if (cookies [i].getName().equals (cookieName)){
				myCookie = cookies[i];
				break;
			}
		}
	}else{
		
		Cookie cookie = new Cookie ("newCookie","newCookie");
		cookie.setMaxAge(60);
		response.addCookie(cookie);
		System.out.println("Cookie added to master......");
	}
	
	
	 response.sendRedirect("http://www.slave.com:8080/slave/return.jsp");	

%>

</body>
</html>