package com.sones.businessLogic.KeywordManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sones.businessLogic.Searcher.IKeywordRetriever;
import com.sones.businessLogic.UserManager.ApplicationUserID;
import com.sones.businessLogic.UserManager.IApplicationUserID;

import	static	org.junit.Assert.*;

public class KeywordRetrieverTest 
{
	private	IKeywordRetriever	_keywordRetriever;
	private	IApplicationUserID	_userID;
	
	@Before
	public	void	SetUp()
	{
		_keywordRetriever	=	new	KeywordRetriever();
		_userID	=	new	ApplicationUserID( "1" );
	}
	
	@After
	public	void	TearDown()
	{
		_keywordRetriever	=	null;
	}

	@Test
	public	void	GetKeywords_KewordsNotNull_Test()
	{
		assertNotNull( _keywordRetriever.GetKeywords( _userID ) );
	}
	
	@Test
	public	void	GetKeywords_KewordValueNotEmpty_Test()
	{
		assertFalse( _keywordRetriever.GetKeywords( _userID ).GetIterator().next().GetValue().isEmpty() );
	}
}
