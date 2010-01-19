package com.timesgroup.sso.constants;

import javax.mail.*;
import javax.mail.internet.*;

import com.timesgroup.sso.utils.TimesMail;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.*;
/**
  * A simple email sender class.
  */
public class SimpleSender
{
  /**
    * Main method to send a message given on the command line.
    */
	
	
	private static String emailTemplate = null ;
	static {
	InputStream  is =	TimesMail.class.getResourceAsStream("/com/timesgroup/sso/templates/itimes_invitation.html");
	char[] buffer = new char[0x10000];
	StringBuilder out = new StringBuilder();
	Reader in = null ;
	try {
		in = new InputStreamReader(is, "UTF-8");
	} catch (UnsupportedEncodingException e) {
		
		e.printStackTrace();
	}
	int read = 0;
	do {
	  try {
		read = in.read(buffer, 0, buffer.length);
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	  if (read>0) {
	    out.append(buffer, 0, read);
	  }
	}
	while (read>=0);
	
	emailTemplate = out.toString() ;
	//System.out.println(emailTemplate);

	}
	
	
  public static void main(String args[])
  {
    try
    {
      String smtpServer="192.168.58.135";
      String to="nitin.gautam@indiatimes.co.in";
      String from="nitin.gautam@indiatimes.co.in";
      String subject="nitin.gautam@indiatimes.co.in";
      String body=String.format(emailTemplate,
				" singh", "singh","singh","singh","singh","singh" ,"singh"
		);
      send(smtpServer, to, from, subject, body);
    }
    catch (Exception ex)
    {
      System.out.println("Usage: java com.lotontech.mail.SimpleSender"
       +" smtpServer toAddress fromAddress subjectText bodyText");
    }
    System.exit(0);
    
  }
  public static void send(String smtpServer, String to, String from
		   , String subject, String body)
		  {
		    try
		    {
		      Properties props = System.getProperties();
		      // -- Attaching to default Session, or we could start a new one --
		      props.put("mail.smtp.host", smtpServer);
		      Session session = Session.getDefaultInstance(props, null);
		      // -- Create a new message --
		      Message msg = new MimeMessage(session);
		      // -- Set the FROM and TO fields --
		      msg.setFrom(new InternetAddress(from));
		      msg.setRecipients(Message.RecipientType.TO,
		        InternetAddress.parse(to, false));
		      // -- We could include CC recipients too --
		      // if (cc != null)
		      // msg.setRecipients(Message.RecipientType.CC
		      // ,InternetAddress.parse(cc, false));
		      // -- Set the subject and body text --
		      msg.setSubject(subject);
		      msg.setText(body);
		      // -- Set some other header information --
		      msg.setHeader("X-Mailer", "LOTONtechEmail");
		      msg.setSentDate(new Date());
		      // -- Send the message --
		      Transport.send(msg);
		      System.out.println("Message sent OK.");
		    }
		    catch (Exception ex)
		    {
		      ex.printStackTrace();
		    }
		  }
		

  }
