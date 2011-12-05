package com.sones.businessLogic.KeywordManager;

import com.sones.businessLogic.UserManager.IApplicationUserID;

public interface IUserKeywordSaver 
{
	public	boolean	SaveKeyword( IApplicationUserID userID, String keyword);
}