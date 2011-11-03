package com.sones.dao.Facebook.Feed.Contents;

import com.sones.businessLogic.Facebook.FeedManager.IFacebookFeedContent;
import com.sones.businessLogic.Facebook.FeedManager.LinkContent;
import com.sones.businessLogic.Facebook.FeedManager.NoteContent;
import com.sones.dao.AbstractDao;

public class NoteContentDao	extends AbstractDao	implements	IFacebookContentDao
{
	public	NoteContentDao()
	{
		super();
	}

	@Override
	public void SaveContent( final IFacebookFeedContent content ) 
	{
		super.saveOrUpdate( ( ( NoteContent )content ) );
	}
	
	@Override
	public IFacebookFeedContent GetContent( final String feedID ) 
	{
		IFacebookFeedContent	content	=	null;
		if( false == IsNull( feedID ) )
		{
			content	=	(IFacebookFeedContent) super.find( NoteContent.class , feedID );
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
