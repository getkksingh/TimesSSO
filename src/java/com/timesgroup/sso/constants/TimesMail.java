package com.timesgroup.sso.constants;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Queue;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Constants;
import com.timesgroup.sso.hibernate.mapping.UserInvitation;

public class TimesMail implements Runnable{

	private static TimesMail timesMailInstance=null;
	
	private static final String SMTP_HOST_NAME = "smtp.gmail.com";
	private static final int SMTP_HOST_PORT = 465;
	private static final String SMTP_AUTH_USER = "gautamn2002@gmail.com";
	private static final String SMTP_AUTH_PWD = "I2005dCS";
	
	public static Queue<UserInvitation> mailInvitations = new LinkedList<UserInvitation>();
	final Logger mylogger = Logger.getLogger(TimesMail.class);
	
	private TimesMail() {
		
	}
	
	public static TimesMail getInstance() {
		
		if(timesMailInstance==null) {
			timesMailInstance=new TimesMail();
		}
		
		return timesMailInstance;
	}

	public void run() {
		while(true) {
		
		Properties props = new Properties();

		props.put("mail.transport.protocol", "smtps");
		props.put("mail.smtps.host", SMTP_HOST_NAME);
		props.put("mail.smtps.auth", "true");
		// props.put("mail.smtps.quitwait", "false");

		Session mailSession = Session.getDefaultInstance(props);
		// mailSession.setDebug(true);
		Transport transport;
		try {
			transport = mailSession.getTransport();
			MimeMessage message = new MimeMessage(mailSession);
			message.setSubject("Invitation from ITimes");
			
			if(this.mailInvitations!=null && this.mailInvitations.size()>0)
				transport.connect(SMTP_HOST_NAME, SMTP_HOST_PORT, SMTP_AUTH_USER,
						SMTP_AUTH_PWD);

			UserInvitation userInvitation=null;
			while((userInvitation=this.deQueueInvitation())!=null){
				
				//System.out.println("TimesMail.run()"+userInvitation.getEmailId());
				//System.out.println("TimesMail.run()"+this.mailInvitations.size());
				
				message.setContent("Click on the link to join itimes\n\n"
						+"http://"+SSOConstants.ServerIP+"/InsertUserProfile?hashCode="+
						userInvitation.getHashCode()+"\n", "text/plain");
				
				message.setRecipient(Message.RecipientType.TO, new InternetAddress(
						userInvitation.getEmailId()));	

				transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
				mylogger.info("Mail send to "+userInvitation.getEmailId());
			}
			
			transport.close();
			
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
	
	public synchronized void enQueueInvitation(UserInvitation userInvitation){
		
		mailInvitations.add(userInvitation);
	}
	
	public synchronized UserInvitation deQueueInvitation(){
		
		if(mailInvitations.size()==0) return null;
		
		return mailInvitations.remove();
	}

	

}
