package com.sones.businessLogic.Searcher.Facebook;

import com.sones.businessLogic.Facebook.FeedManager.FacebookFeed;
import com.sones.businessLogic.Facebook.FeedManager.IFacebookFeedContent;
import com.sones.businessLogic.Facebook.FeedManager.CommentManager.IFacebookComments;
import com.sones.businessLogic.Searcher.ISearchableFeed;
import com.sones.dao.Facebook.Feed.FacebookFeedDao;
import com.sones.dao.Facebook.Feed.IFacebookFeedDao;
import com.sones.dao.Facebook.Feed.IFacebookPersistableFeed;
import com.sones.dao.Facebook.Feed.Comments.FacebookCommentDao;
import com.sones.dao.Facebook.Feed.Comments.IFacebookCommentDao;
import com.sones.dao.Facebook.Feed.Contents.FacebookContentDaoFactory;
import com.sones.dao.Facebook.Feed.Contents.IFacebookContentDaoFactory;

public class FacebookFeedIntegrator	implements	IFacebookFeedIntegrator
{
	private	IFacebookFeedDao	_feedDao;
	private	IFacebookCommentDao	_commentDao;
	private	IFacebookContentDaoFactory	_contentDaoFactory;
	
	public	FacebookFeedIntegrator()
	{
		_feedDao	=	new	FacebookFeedDao();
		_commentDao	=	new	FacebookCommentDao();
		_contentDaoFactory	=	new	FacebookContentDaoFactory();
	}

	@Override
	public ISearchableFeed GetFeed( String feedID ) 
	{
		IFacebookPersistableFeed	persistableFeed	=	_feedDao.GetFeed( feedID );
		IFacebookFeedContent	feedContent	=	_contentDaoFactory.GetContentDao( persistableFeed.GetType() ).GetContent( persistableFeed.GetID() );
		IFacebookComments	feedComments	=	_commentDao.GetComments( feedID );
		
		ISearchableFeed	feed	=	new	FacebookFeed();
		( ( FacebookFeed ) feed ).SetComments( feedComments );
		( ( FacebookFeed ) feed ).SetContent( feedContent );
		( ( FacebookFeed ) feed ).SetId( persistableFeed.GetID() );

		return	feed;
	}

}
