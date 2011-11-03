package com.sones.dao.Facebook.Feed.Contents;

import org.apache.log4j.Logger;

import com.sones.businessLogic.Facebook.FeedManager.IFacebookFeedContent;
import com.sones.businessLogic.Facebook.FeedManager.LinkContent;
import com.sones.businessLogic.Facebook.FeedManager.VideoContent;
import com.sones.dao.AbstractDao;

public class VideoContentDao	extends	AbstractDao	implements	IFacebookContentDao
{
	private	Logger	_logger;
	
	public	VideoContentDao()
	{
		super();
	}

	@Override
	public void SaveContent( final IFacebookFeedContent content ) 
	{	
		boolean	isVideoSaved	=	super.saveOrUpdate( ( (VideoContent)content ) );
		if( false == isVideoSaved )
		{
			_logger.error( "Problem saving video" );
		}
	}
	
	@Override
	public IFacebookFeedContent GetContent( final String feedID ) 
	{
		IFacebookFeedContent	content	=	null;
		if( false == IsNull( feedID ) )
		{
			content	=	(IFacebookFeedContent) super.find( VideoContent.class , feedID );
		}
		return	content;
	}
	
	private	boolean	IsNull( final String feedID )
	{
		if( null == feedID )
		{
			return	true;
		}
		return	false;
	}
}
