package com.sones.facebook.datemanager.logic;

import java.util.Date;

public class FacebookDateManager	implements	IFacebookDateManager
{

	@Override
	public String getDateForGraphApiSearch(Date date) 
	{
		return new	String("0");
	}
	
}
