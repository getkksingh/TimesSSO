package com.timesgroup.sso.constants;

import java.io.PrintStream;

public class Main {
public static void main(String[] args) {
	String targetUrl = "http://integra.indiatimes.com/Times/Logon.aspx?ru=http://astro.indiatimes.com/AstrospeakWebApp/servlet/MemberManager?RequestID=8888&IS=17f01fb0-2cae-4f83-b94e-d6e7c6027fc4&NS=Astro&HS=n98HjbpFmKK0cCLquQzuD9LLNds=" ;
	String UserID = "kksingh_iitk" ;
	String Password = "old314321" ;
	PrintStream out = System.out ;
	out.println("<HTML><HEAD><TITLE>Please Wait..........</TITLE>");
	out.println("<SCRIPT LANGUAGE=JAVASCRIPT>");
	out.println("function redirectToIntegra()");
	out.println("{");
	out.println("document.integra.submit();");
	out.println("}");
	out.println("</SCRIPT>");
	out.println("</HEAD>");
	out.println("");
	out.println("<body bgcolor='#FFFFFF' text='#000000' link='#0000FF' vlink='#800080' alink='#FF0000' onLoad='JavaScript:redirectToIntegra();'>");
	out.println("<center><b><font face='arial' size='3'>");
	out.println("<br><br>");
	out.println("Please wait.<br>Please do not click on the browser back button.</font></b></center>");
	out.println("<FORM NAME='integra' METHOD='POST' ACTION='"+targetUrl+"'>");
	out.println("<INPUT TYPE='HIDDEN' NAME='login' VALUE='"+UserID+"'>");
	out.println("<INPUT TYPE='HIDDEN' NAME='passwd' VALUE='"+Password+"'>");
	out.println("<INPUT TYPE='HIDDEN' NAME='newreg' VALUE='http://astro.indiatimes.com/AstrospeakWebApp/servlet/MemberManager?RequestID=110?target=null&redirect='>");
	out.println(" ");
	out.println("</FORM>");
	out.println("</BODY>");
	out.println("</HTML>");
}
}
