package com.sones.businessLogic.KeywordManager;

import java.util.List;

public interface IUserKeywordRetriever 
{
	public	IKeywords	GetKeywords( final List< String >	keywordIDs );
}
