package com.sones.businessLogic.Facebook.JSON;

import org.apache.commons.beanutils.DynaBean;

import com.sones.businessLogic.Facebook.FeedManager.FacebookFeed;
import com.sones.businessLogic.Facebook.FeedManager.IFacebookFeed;
import com.sones.businessLogic.Facebook.FeedManager.IFacebookFeedContent;
import com.sones.businessLogic.Facebook.FeedManager.CommentManager.IFacebookComments;
import com.sones.utilities.SonesLogger;

public class FacebookFeedFactoryFromDynaBean	implements	IFacebookFeedFactory
{
	private	IFacebookFeedContentFactory	_contentFactory;
	private	IFacebookFeedCommentsFactory	_commentFactory;
	
	public	FacebookFeedFactoryFromDynaBean()
	{
		_contentFactory	=	new	FacebookFeedContentFactoryFromDynaBean();
		_commentFactory	=	new	FacebookFeedCommentsFactoryFromDynaBean();
	}
	
	public	FacebookFeedFactoryFromDynaBean( final IFacebookFeedContentFactory contentFactory )
	{
		if( null != contentFactory )
		{
			_contentFactory	=	contentFactory;
		}
		_commentFactory	=	new	FacebookFeedCommentsFactoryFromDynaBean();
	}
	
	@Override
	public IFacebookFeed GetFeed( final Object feedObject ) 
	{
		IFacebookFeed	facebookFeed	=	null;
		if( null != feedObject)
		{
			DynaBean	feed	=	( DynaBean )feedObject;
			facebookFeed	=	GetFeed( feed );
			
			IFacebookFeedContent	feedContent	=	_contentFactory.GetFeedContent( feedObject );
			SetFacebookFeedContent( facebookFeed, feedContent );
			
			IFacebookComments	comments = _commentFactory.GetComments(feedObject);
			SetFacebookFeedComments( facebookFeed, comments  );
		}
		else	if( null == feedObject )
		{
			SonesLogger.Error( "Dyna feed object is null" );
		}
		if( null == facebookFeed )
		{
			SonesLogger.Error( "Facebook feed object is null" );
		}
		return	facebookFeed;
	}
	
	private	IFacebookFeed	GetFeed( final DynaBean object )
	{
		String	id	=	GetId( object );
		String 	createdTime	=	GetCreationDate( object );
		String 	creatorId = GetFeedCreatorId( object );
		IFacebookFeed	feed	=	new	FacebookFeed(id);
		return	feed;
	}
	
	private	void	SetFacebookFeedContent( final IFacebookFeed feed, final IFacebookFeedContent content )
	{
		( ( FacebookFeed ) feed ).SetContent( content );
		( ( FacebookFeed ) feed ).set_type( content.GetContentType() );
	}
	
	private	void	SetFacebookFeedComments( final IFacebookFeed feed, final IFacebookComments comments )
	{
		( ( FacebookFeed ) feed ).SetComments( comments );
	}
	
	private	String	GetCreationDate( final DynaBean object )
	{
		return	object.get( "created_time" ).toString();
	}
	
	private	String	GetId( final DynaBean object )
	{
		return	object.get( "id" ).toString();
	}
	
	private	String	GetFeedCreatorId( final DynaBean object )
	{
		return	( ( DynaBean )( object.get( "from" ) ) ).get( "id" ).toString();
	}
	

}
