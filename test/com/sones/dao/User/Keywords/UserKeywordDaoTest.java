package com.sones.dao.User.Keywords;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sones.businessLogic.UserManager.ApplicationUserID;
import com.sones.businessLogic.UserManager.IApplicationUserID;

import	static	org.junit.Assert.*;

public class UserKeywordDaoTest 
{
	private IApplicationUserID	_userID;
	private	UserKeywordDao	_dao;
	
	@Before
	public	void	SetUp()
	{
		_dao	=	new	UserKeywordDao();
		_userID	=	new	ApplicationUserID( "1" );
	}
	
	@After
	public	void	TearDown()
	{
		_dao	=	null;
		_userID	=	null;
	}
	
	@Test
	public	void	GetKeywords_SizeNotZero_Test()
	{
		assertTrue( _dao.GetKeywords( _userID ).size() > 0 );
	}

	@Test
	public	void	GetKeywords_KeywordIDNotEmpty_Test()
	{
		assertFalse( _dao.GetKeywords( _userID ).get(0).isEmpty() );
	}
	
	@Test
	public	void	SaveKeyword_KeywordDoesNotExist_Test()
	{
		assertTrue( _dao.SaveKeyword( _userID , "Tsifsa") );
	}
}
