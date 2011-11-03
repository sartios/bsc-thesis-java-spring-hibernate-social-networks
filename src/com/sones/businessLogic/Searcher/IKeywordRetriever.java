package com.sones.businessLogic.Searcher;

import com.sones.businessLogic.KeywordManager.IKeywords;
import com.sones.businessLogic.UserManager.IApplicationUserID;

public interface IKeywordRetriever 
{
	public	IKeywords	GetKeywords( final IApplicationUserID userID );
}
