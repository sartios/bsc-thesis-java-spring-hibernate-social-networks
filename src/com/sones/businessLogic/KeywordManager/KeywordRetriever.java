package com.sones.businessLogic.KeywordManager;

import java.util.List;

import com.sones.businessLogic.KeywordManager.IKeywords;
import com.sones.businessLogic.Searcher.IKeywordRetriever;
import com.sones.businessLogic.UserManager.IApplicationUserID;
import com.sones.dao.Keywords.KeywordDao;
import com.sones.dao.User.Keywords.UserKeywordDao;

public class KeywordRetriever	implements	IKeywordRetriever
{
	private	IUserKeywordIDRetriever	_keywordIDRetriever;
	private	IUserKeywordRetriever	_keywordRetriver;

	public	KeywordRetriever()
	{
		_keywordIDRetriever	=	new	UserKeywordDao();
		_keywordRetriver	=	new	KeywordDao();
	}
	
	@Override
	public IKeywords GetKeywords(IApplicationUserID userID) 
	{
		List< String >	keywordIDs	=	_keywordIDRetriever.GetKeywords( userID );
		IKeywords	keywords	=	_keywordRetriver.GetKeywords( keywordIDs );
		return	keywords;
	}
	
}
