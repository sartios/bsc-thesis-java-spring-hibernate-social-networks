package com.sones.facebook.keywordSearcher.logic;

import java.util.Date;

public interface IKeywordSearcherManagerService 
{
	//void startKeywordSearch(String appUserId);
	void stopKeywordSearch();
	void saveOptions(String appUserId, String interval);
	void startKeywordSearch(String appUserId, Date date);
	void setSearchService(IKeywordSearcherService service);
	String getSearchInterval(String appUserId);
}
