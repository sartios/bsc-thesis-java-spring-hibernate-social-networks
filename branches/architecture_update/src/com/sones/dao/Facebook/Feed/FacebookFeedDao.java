package com.sones.dao.Facebook.Feed;


import org.apache.log4j.Logger;

import com.sones.businessLogic.Facebook.FeedManager.FacebookFeed;
import com.sones.businessLogic.Facebook.FeedManager.IFacebookFeed;
import com.sones.dao.AbstractDao;

public	class	FacebookFeedDao	extends	AbstractDao	implements	IFacebookFeedDao
{
	private	Logger	_logger;
	public	FacebookFeedDao()
	{
		super();
		_logger	=	Logger.getLogger( FacebookFeedDao.class );
	}

	@Override
	public void SaveFacebookFeed( IFacebookFeed feed ) 
	{
		_logger.debug( "Saving feed: " + feed.GetID() );
		super.saveOrUpdate( feed );
	}

	@Override
	public IFacebookPersistableFeed GetFeed(String feedID) 
	{
		FacebookFeed	feed	=	null;
		if( false == isNull( feedID ) )
		{
			feed	=	(FacebookFeed) super.find( FacebookFeed.class , feedID );
		}
		return	feed;
			
	}
	
	private	boolean isNull( final String feedID )
	{
		if( null == feedID)
		{
			_logger.error(" Feed ID is null. Enter a valid feed ID." );
			return	true;
		}
		return	false;
	}
}
