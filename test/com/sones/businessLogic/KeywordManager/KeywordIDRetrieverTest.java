package com.sones.businessLogic.KeywordManager;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sones.businessLogic.UserManager.ApplicationUserID;
import com.sones.businessLogic.UserManager.IApplicationUserID;
import com.sones.dao.User.Keywords.UserKeywordDao;

public class KeywordIDRetrieverTest 
{
	private	IUserKeywordIDRetriever	_keywordIDRetriever;
	private	IApplicationUserID	_userID;
	
	@Before
	public	void	SetUp()
	{
		_keywordIDRetriever	=	new	UserKeywordDao();
		_userID	=	new	ApplicationUserID( "1" );
	}
	
	@After
	public	void	TearDown()
	{
		_keywordIDRetriever	=	null;
	}

	@Test
	public	void	GetKeywords_KewordsNotNull_Test()
	{
		assertNotNull( _keywordIDRetriever.GetKeywords( _userID ) );
	}
}
