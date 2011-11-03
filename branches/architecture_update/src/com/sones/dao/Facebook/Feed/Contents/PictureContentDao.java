package com.sones.dao.Facebook.Feed.Contents;

import com.sones.businessLogic.Facebook.FeedManager.IFacebookFeedContent;
import com.sones.businessLogic.Facebook.FeedManager.LinkContent;
import com.sones.businessLogic.Facebook.FeedManager.PictureContent;
import com.sones.dao.AbstractDao;

public class PictureContentDao extends AbstractDao implements IFacebookContentDao
{

	public	PictureContentDao()
	{
		super();
	}

	@Override
	public void SaveContent( final IFacebookFeedContent content ) 
	{
		super.saveOrUpdate( ( (PictureContent)content ) );
	}
	
	@Override
	public IFacebookFeedContent GetContent( final String feedID ) 
	{
		IFacebookFeedContent	content	=	null;
		if( false == IsNull( feedID ) )
		{
			content	=	(IFacebookFeedContent) super.find( PictureContent.class , feedID );
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
