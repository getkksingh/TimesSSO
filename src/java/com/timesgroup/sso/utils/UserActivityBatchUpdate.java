package com.timesgroup.sso.utils;

import java.util.Vector;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.timesgroup.sso.hibernate.mapping.UserActivity;

public class UserActivityBatchUpdate extends Thread
{
	Thread useractivity_thread;
	boolean StopThread = false;
	Vector batches=null;
	
	public UserActivityBatchUpdate(Vector batches)
	{
		super("UserActivity");
		this.batches=batches;
		try{
			useractivity_thread = new Thread(this);
			useractivity_thread.setPriority(Thread.MIN_PRIORITY);
			useractivity_thread.start();
		}catch(Exception e)
		{
			System.out.println("Error .");
		}
	}

	public void start()
	{
		System.out.println(new java.util.Date()+" UserActivity Thread Started.....");
	}

	public void  run()
	{
		addUserActivity();		
		System.out.println("useractivity_thread Finished.....");
		useractivity_thread=null;
	}

	private void addUserActivity() {
	 	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	 	
        Transaction transaction = session.beginTransaction();   
        for (int i =0;i<batches.size();i++)
        {
        	UserActivity userActivity = (UserActivity)batches.elementAt(i);
            session.save(userActivity);
            if (i%50 == 0)
            {
                session.flush();
                session.clear();
            }
        }
        transaction.commit();
	}
}
