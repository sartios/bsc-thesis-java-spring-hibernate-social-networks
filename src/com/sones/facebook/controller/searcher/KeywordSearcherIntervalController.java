package com.sones.facebook.controller.searcher;

import org.apache.log4j.Logger;

import com.sones.facebook.keywordSearcher.logic.IKeywordSearcherManagerService;

public class KeywordSearcherIntervalController 
{
	private final Logger _LOGGER;
	private IKeywordSearcherManagerService service = null;
	
	public KeywordSearcherIntervalController()
	{
		_LOGGER = Logger.getLogger(KeywordSearcherIntervalController.class);
	}
	
	public boolean saveOptions(String appUserId, String interval)
	{
		if(appUserId == null)
		{
			return false;
		}
		if(interval == null)
		{
			return false;
		}
		if(service == null)
		{
			_LOGGER.error("Service was not setted!");
			return false;
		}
		try
		{
			service.saveOptions(appUserId, interval);
		}
		catch (RuntimeException e) {
			return false;
		}
		return true;
	}
	
	public void setService(IKeywordSearcherManagerService service)
	{
		this.service = service;
	}
}
