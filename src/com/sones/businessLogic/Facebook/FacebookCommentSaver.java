package com.sones.businessLogic.Facebook;

import java.util.List;

import org.apache.log4j.Logger;

import com.sones.businessLogic.Facebook.FeedManager.CommentManager.IFacebookComments;
import com.sones.businessLogic.UserManager.IApplicationUserID;
import com.sones.dao.Facebook.Feed.Comments.FacebookCommentDao;
import com.sones.dao.Facebook.Feed.Comments.IFacebookCommentDao;
import com.sones.dao.User.Facebook.Feed.Comments.IUserFacebookCommentsDao;
import com.sones.dao.User.Facebook.Feed.Comments.UseFacebookCommentsDao;

public class FacebookCommentSaver implements	IFacebookCommentSaver
{
	private	IFacebookCommentDao	_commentDao;
	private	IUserFacebookCommentsDao _userCommentsDao;
	private	Logger	_logger;
	
	public	FacebookCommentSaver()
	{
		_logger	=	Logger.getLogger( FacebookCommentSaver.class );
		_commentDao	=	new	FacebookCommentDao();
		_userCommentsDao	=	new	UseFacebookCommentsDao();
	}
	
	public	FacebookCommentSaver( final IFacebookCommentDao commentDao, final IUserFacebookCommentsDao userCommentDao)
	{
		_logger	=	Logger.getLogger( FacebookCommentSaver.class );
		
		if( ( null != commentDao ) && ( null != commentDao ) )
		{
			_commentDao	=	commentDao;
			_userCommentsDao	=	userCommentDao;
		}
	}

	@Override
	public boolean SaveUserFacebookComments(IApplicationUserID userID, IFacebookComments comments)
	{
		CheckUserID( userID );
		CheckComments( comments );
		GetCommentDao().SaveComments(comments);
		AssociateUserWithComments( userID, comments );
		return false;
		
	}

	@Override
	public boolean AssociateUserWithComments( IApplicationUserID userID, IFacebookComments comments) {
		CheckUserID( userID );
		CheckComments( comments );
		List< String >	commentIDs	=	comments.GetCommentIDs();
		GetUserCommentDao().SaveUserFacebookComments( userID.GetValue(), commentIDs );
		return false;
	}

	@Override
	public boolean SaveComments( IFacebookComments comments)
	{
		CheckComments( comments );
		_logger.warn( "Saving feed's comments" );
		return	GetCommentDao().SaveComments( comments );
	}
	
	private	IUserFacebookCommentsDao	GetUserCommentDao()
	{
		if( null == _userCommentsDao )
		{
			_logger.error( "Object that saves user's comments is null" );
		}
		return	_userCommentsDao;
	}
	
	
	private	IFacebookCommentDao	GetCommentDao()
	{
		if( null == _commentDao )
		{
			_logger.error( "CommentDAO is null" );
		}
		return	_commentDao;
	}
	
	private	void	CheckUserID( IApplicationUserID userID )
	{
		if( null == userID)
		{
			_logger.error( "User ID is null" );
		}
	}
	
	private	void	CheckComments( IFacebookComments comments )
	{
		if( null == comments)
		{
			_logger.error( "Comments are null" );
		}
		else if( 0 == comments.GetSize() )
		{
			_logger.debug( "Comments are empty" );

		}
	}

}
