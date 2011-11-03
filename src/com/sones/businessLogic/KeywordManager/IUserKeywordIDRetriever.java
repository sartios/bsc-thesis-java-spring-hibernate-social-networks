package com.sones.businessLogic.KeywordManager;

import java.util.List;

import com.sones.businessLogic.UserManager.IApplicationUserID;

public interface IUserKeywordIDRetriever 
{
	public	List< String >	GetKeywords( final IApplicationUserID userID );
}
