package com.sones.facebook.datemanager.logic;

import java.util.Date;

public class FacebookDateManager	implements	IFacebookDateManager
{

	@Override
	public String getDateForGraphApiSearch(Date date) 
	{
		Long epoch = date.getTime() / 1000;
		String time = epoch.toString();
		return time;
	}
	
}
