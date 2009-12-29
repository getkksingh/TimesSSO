package com.timesgroup.sso.utils;

import java.util.Vector;

public class UserActivityVector extends Vector {

	public void finalize()
	{
		System.out.println("System Shutting down..");
		new UserActivityBatchUpdate((Vector)this);
	}
}
