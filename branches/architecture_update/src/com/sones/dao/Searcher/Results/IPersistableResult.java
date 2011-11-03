package com.sones.dao.Searcher.Results;

public interface IPersistableResult
{
	public void SetUserID(String userID);
	
	public	String	GetUserID();
	public	String	GetFeedID();
	public	String	GetKeywordID();
}
