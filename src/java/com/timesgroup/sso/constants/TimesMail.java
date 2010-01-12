package com.timesgroup.sso.constants;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Queue;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import com.timesgroup.sso.hibernate.mapping.UserInvitation;

public class TimesMail implements Runnable{

	private static TimesMail timesMailInstance=null;
	private static String SMTP_HOST_NAME = "";
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
		
		try{
			
			InputStream inputStream = TimesMail.class.getResourceAsStream("/com/timesgroup/sso/conf/SSO.properties");  
			Properties properties = new Properties();  
			
			// load the inputStream using the Properties  
			properties.load(inputStream);  
			// get the value of the property  
			SMTP_HOST_NAME = properties.getProperty("SMTPIP");  
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static Queue<UserInvitation> mailInvitations = new LinkedList<UserInvitation>();
	final Logger mylogger = Logger.getLogger(TimesMail.class);
	
	private TimesMail() {}
	
	public static TimesMail getInstance() {
		
		if(timesMailInstance==null) {
			timesMailInstance=new TimesMail();
		}
		return timesMailInstance;
	}

	public void run() {
		while(true) {
		
			try {
			
				UserInvitation userInvitation=null;
				while((userInvitation=this.deQueueInvitation())!=null){
					
					sendMail(mylogger, userInvitation);
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
			  Thread.currentThread().sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	  }
	}
	
	 public static void sendMail(Logger mylogger, UserInvitation userInvitation) {
			   
		 try{
			 	  Properties props = System.getProperties();
			      // -- Attaching to default Session, or we could start a new one --
			      props.put("mail.smtp.host", SMTP_HOST_NAME);
			      Session session = Session.getDefaultInstance(props, null);
			      // -- Create a new message --
			      Message msg = new MimeMessage(session);
			      // -- Set the FROM and TO fields --
			      msg.setFrom(new InternetAddress("nitin.gautam@indiatimes.co.in"));
			      msg.setRecipients(Message.RecipientType.TO,
			        InternetAddress.parse(userInvitation.getEmailId(), false));
			      // -- We could include CC recipients too --
			      // if (cc != null)
			      // msg.setRecipients(Message.RecipientType.CC
			      // ,InternetAddress.parse(cc, false));
			      // -- Set the subject and body text --
			      msg.setSubject("Invitation from ITimes");
			      //msg.setText(body);
			      msg.setContent(
							String.format(emailTemplate,
							userInvitation.getRefereeUserId(),
							SSOConstants.ServerURL+userInvitation.getHashCode()			
							),
							"text/html");
			      // -- Set some other header information --
			      msg.setHeader("X-Mailer", "LOTONtechEmail");
			      msg.setSentDate(new Date());
			      // -- Send the message --
			      Transport.send(msg);
			      mylogger.info("Mail send to "+userInvitation.getEmailId());
			    }
			    catch (Exception ex)
			    {
			      ex.printStackTrace();
			    }
	}
	 
	public synchronized void enQueueInvitation(UserInvitation userInvitation){
		
		mailInvitations.add(userInvitation);
	}
	
	public synchronized UserInvitation deQueueInvitation(){
		
		if(mailInvitations!=null && mailInvitations.size()==0) return null;
		
		return mailInvitations.remove();
	}

	public static void main(String[] args) {
		System.out.println(emailTemplate);
		System.out.println(String.format(emailTemplate,
						" singh", "singh","singh","singh","singh","singh" ,"singh"
		));
	}
	

}
