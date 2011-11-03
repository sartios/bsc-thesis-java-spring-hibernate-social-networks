package com.sones.dao.Facebook.Feed.Contents;

import com.sones.businessLogic.Facebook.FeedManager.IFacebookFeedContent;
import com.sones.businessLogic.Facebook.FeedManager.LinkContent;
import com.sones.dao.AbstractDao;

public class LinkContentDao	extends	AbstractDao	implements	IFacebookContentDao
{
	public	LinkContentDao()
	{
		super();
	}

	@Override
	public void SaveContent( final IFacebookFeedContent content ) 
	{
		super.saveOrUpdate( ( ( LinkContent )content ) );
	}

	@Override
	public IFacebookFeedContent GetContent( final String feedID ) 
	{
		IFacebookFeedContent	content	=	null;
		if( false == IsNull( feedID ) )
		{
			content	=	(IFacebookFeedContent) super.find( LinkContent.class , feedID );
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
