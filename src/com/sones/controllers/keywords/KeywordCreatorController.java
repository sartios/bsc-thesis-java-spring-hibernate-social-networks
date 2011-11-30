package com.sones.controllers.keywords;

import com.sones.businessLogic.KeywordManager.IUserKeywordSaver;
import com.sones.businessLogic.UserManager.IApplicationUserID;
import com.sones.dao.Keywords.KeywordDao;
import com.sones.dao.User.Keywords.UserKeywordDao;

public class KeywordCreatorController	implements	IKeywordCreatorController
{
	private	IUserKeywordSaver	_keywordSaver;
	
	public	KeywordCreatorController()
	{
		_keywordSaver	=	new	UserKeywordDao();
	}

	@Override
	public boolean SaveUserKeyword(IApplicationUserID userID, String keywordValue) 
	{
		if( ( null != userID ) && ( null != keywordValue ))
		{
			return	_keywordSaver.SaveKeyword(userID, keywordValue);
		}
		return	false;
	}

}
