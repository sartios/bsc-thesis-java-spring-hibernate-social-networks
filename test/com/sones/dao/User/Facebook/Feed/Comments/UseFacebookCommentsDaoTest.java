package com.sones.dao.User.Facebook.Feed.Comments;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import	static	org.junit.Assert.*;

public class UseFacebookCommentsDaoTest 
{
	private	IUserFacebookCommentsDao	_userCommentsDao;
	private	Logger	_logger;
	
	@Before
	public	void	SetUp()
	{
		_logger	=	Logger.getLogger(UseFacebookCommentsDaoTest.class);
		_userCommentsDao	=	new	UseFacebookCommentsDao();
	}
	
	@After
	public	void	TearDown()
	{
		_logger	=	null;
		_userCommentsDao	=	null;
	}
	
	@Test
	public	void	GetInsertSqlString_CheckCorrectString_Test()
	{
		assertFalse( ( ( UseFacebookCommentsDao )_userCommentsDao ).GetInsertSqlString() .isEmpty() );
		//_logger.error( ( (UseFacebookCommentsDao)_userCommentsDao ).GetInsertSqlString() );
	}
	
	@Test
	public	void	SaveUserFacebookComments_UserAndCommentsExist_Test()
	{
		String	userID	=	"1";
		List< String > commentIDs	=	new	ArrayList< String >();	
		commentIDs.add( new String( "Comment ID" ) );
		assertTrue( _userCommentsDao.SaveUserFacebookComments(userID, commentIDs) );
	}
	
	@Test
	public	void	SaveUserFacebookComments_UserDoesNotExist_Test()
	{
		String	userID	=	"xxx";
		List< String > commentIDs	=	new	ArrayList< String >();	
		commentIDs.add( new String( "Comment ID" ) );
		assertFalse( _userCommentsDao.SaveUserFacebookComments(userID, commentIDs) );
	} 
	
	@Test
	public	void	SaveUserFacebookComments_CommentsUserDoesNotExist_Test()
	{
		String	userID	=	"1";
		List< String > commentIDs	=	new	ArrayList< String >();	
		commentIDs.add( new String( "Comment xxx" ) );
		assertFalse( _userCommentsDao.SaveUserFacebookComments(userID, commentIDs) );
	}
	
}
