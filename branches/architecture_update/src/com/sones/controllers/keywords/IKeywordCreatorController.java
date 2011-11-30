package com.sones.controllers.keywords;

import com.sones.businessLogic.UserManager.IApplicationUserID;

public interface IKeywordCreatorController 
{
	public	boolean	SaveUserKeyword( IApplicationUserID userID , String keywordValue );
}
