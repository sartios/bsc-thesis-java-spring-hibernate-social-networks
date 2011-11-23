package com.sones.businessLogic.Facebook;


import org.apache.log4j.Logger;

import com.sones.buisinessLogic.Facebook.UserManager.IFacebookUserID;
import com.sones.businessLogic.Facebook.FeedManager.AbstractFacebookFeedContent;
import com.sones.businessLogic.Facebook.FeedManager.IFacebookFeed;
import com.sones.businessLogic.Facebook.FeedManager.IFacebookFeedContent;
import com.sones.businessLogic.Facebook.FeedManager.IFacebookFeeds;
import com.sones.businessLogic.Facebook.FeedManager.CommentManager.IFacebookComments;
import com.sones.businessLogic.UserManager.IApplicationUserID;
import com.sones.dao.Facebook.Feed.IFacebookFeedDao;
import com.sones.dao.Facebook.Feed.Contents.IFacebookContentDaoFactory;
import com.sones.dao.User.Facebook.Feeds.IUserFacebookFeedsDao;
import com.sones.dao.User.Facebook.Feeds.UserFacebookFeedsDao;

/**
 * 
 * @author Savramis Sartios
 *
 */
public class FacebookFeedSaver implements	IFacebookFeedSaver
{
	/**
	 * {@link IFacebookFeedDao}
	 */
	private	IFacebookFeedDao		_feedDao;
	
	/**
	 * {@link IUserFacebookFeedsDao}
	 */
	private	IUserFacebookFeedsDao	_userFeedDao;
	
	/**
	 * {@link IFacebookContentDaoFactory}
	 */
	private	IFacebookContentDaoFactory 	_feedContentDaoFacebory;
	
	/**
	 * {@link IFacebookCommentSaver}
	 */
	private	IFacebookCommentSaver	_userFacebookCommentsSaver	=	null;
	
	/**
	 * {@link Logger}
	 */
	private	Logger	_logger;
	
	/**
	 * Initiates the {@link IUserFacebookFeedsDao}
	 */
	public	FacebookFeedSaver()
	{
		_logger	=	Logger.getLogger( FacebookFeedSaver.class );
		_userFeedDao	=	new	UserFacebookFeedsDao();
		_userFacebookCommentsSaver	=	new	FacebookCommentSaver();
	}
	
	/**
	 * Initiates {@link IFacebookFeedDao}, {@link IFacebookContentDaoFactory} and {@link IFacebookFeedDao}
	 * 
	 * @param feedDao {@link IFacebookFeedDao}
	 * @param feedContentDaoFacebory {@link IFacebookContentDaoFactory}
	 */
	public	FacebookFeedSaver( final IFacebookFeedDao feedDao, final IFacebookContentDaoFactory  feedContentDaoFacebory)
	{
		_logger	=	Logger.getLogger( FacebookFeedSaver.class );
		if( null != feedDao)
		{
			_feedDao	=	feedDao;
		}
		if( null != feedContentDaoFacebory)
		{
			_feedContentDaoFacebory	=	feedContentDaoFacebory;
		}
		_userFeedDao	=	new	UserFacebookFeedsDao();
		_userFacebookCommentsSaver	=	new	FacebookCommentSaver();
	}
	
	@Override
	public boolean SaveUserFacebookFeeds( IApplicationUserID userID, IFacebookFeeds feeds ) 
	{
		SaveFeeds( userID, feeds );
		SaveUserFeeds( userID, feeds );
		return true;
	}
	
	private	void	SaveUserFeeds( IApplicationUserID userID, IFacebookFeeds feeds )
	{
		_userFeedDao.SaveUserFacebookFeeds( userID.GetValue(), feeds.GetFeedIDs());

	}
	
	/**
	 * Saves user feeds and the comments that each feed contains. Comments and feeds
	 * are associated with the {@link IFacebookUserID}
	 * 
	 * @param userID {@link IFacebookUserID}
	 * @param feeds {@link IFacebookFeeds}
	 */
	private	void	SaveFeeds( final IApplicationUserID userID, final IFacebookFeeds feeds )
	{
		for( int index = 0; index < feeds.GetSize(); index++)
		{	
			SaveFeed( feeds.GetFeed( index ) );
			SaveComments( userID , feeds.GetFeed( index ).GetComments() );
		}
	}
	
	/**
	 * Saves feed and its {@link IFacebookFeedContent} content
	 * @param feed {@link IFacebookFeed}
	 */
	private	void	SaveFeed( final IFacebookFeed feed )
	{
		_feedDao.SaveFacebookFeed(feed);
		IFacebookFeedContent	content	=	 feed.GetContent();
		( ( AbstractFacebookFeedContent )content).SetFeedID(feed.GetID());
		_feedContentDaoFacebory.GetContentDao( content.GetContentType() ).SaveContent(content);
	}
	
	/**
	 * Saves the comments with their content. It automatically associates the {@link IFacebookUserID} user with them
	 * @param userID {@link IApplicationUserID}
	 * @param comments {@link IFacebookComments}
	 */
	private	void	SaveComments( final IApplicationUserID userID, final IFacebookComments comments )
	{
		ValidateFacebookComments( comments );
		ValidateUserID( userID );
		if( null == _userFacebookCommentsSaver )
		{
			_logger.error( "_userFacebookCommentsSaver ID is null" );
		}
		_userFacebookCommentsSaver.SaveUserFacebookComments( userID ,  comments );
	}
	
	private	void	ValidateUserID( final IApplicationUserID userID )
	{
		if( null == userID)
		{
			_logger.error( "User ID is null" );
		}
	}
	
	private	void	ValidateFacebookComments( final IFacebookComments comments )
	{
		if( null == comments)
		{
			_logger.error( "Comments are null" );
		}
	}

}
