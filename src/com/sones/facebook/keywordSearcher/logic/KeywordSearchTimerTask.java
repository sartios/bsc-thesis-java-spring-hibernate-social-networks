package com.sones.facebook.keywordSearcher.logic;

import java.util.Date;
import java.util.TimerTask;

import com.sones.usermanager.model.ApplicationUser;

public class KeywordSearchTimerTask extends TimerTask
{
	private IKeywordSearcherService service;
	private ApplicationUser appUser;
	
	public KeywordSearchTimerTask(IKeywordSearcherService service)
	{
		this.service = service;
	}
	
	@Override
	public void run()
	{
		service.searchForKeywordsIntoAllFacebookPostTypes(appUser);
	}
	
	public void setApplicationUser(ApplicationUser appUser)
	{
		this.appUser = appUser;
	}

	public void setFirstDate(Date date) 
	{
		service.setNextDateAfterWhichWillRetrieveData(date);
	}
}
