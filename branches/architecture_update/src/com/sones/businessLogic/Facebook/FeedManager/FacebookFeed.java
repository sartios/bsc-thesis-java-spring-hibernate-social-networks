package com.sones.businessLogic.Facebook.FeedManager;

import com.sones.businessLogic.Facebook.FeedManager.CommentManager.IFacebookComments;
import com.sones.businessLogic.Searcher.ISearchableContent;
import com.sones.businessLogic.Searcher.ISearchableFeed;
import com.sones.dao.Facebook.Feed.IFacebookPersistableFeed;

public class FacebookFeed	implements	IFacebookFeed,	IFacebookCommentedFeed, ISearchableFeed, IFacebookPersistableFeed
{
	private	String	_id	=	null;
	private	IFacebookFeedContent	_content	=	null;
	private	IFacebookComments	_comments	=	null;
	private	String	_type	=	null;
	
	public	FacebookFeed()
	{
	}
	
	public	FacebookFeed( final	String	id )
	{
		SetId( id );
	}
	
	public	FacebookFeed( final IFacebookFeedContent content )
	{
		SetContent( content );
	}
	
	public	FacebookFeed( final	String	id,	final	IFacebookFeedContent	content )
	{
		SetId( id );
		SetContent( content );
	}
	
	public	void	SetId( final	String	id)
	{
		if( null != id )
		{
			_id	=	id;
		}
	}
	
	public	String	GetID()
	{
		return	_id;
	}
	
	public	void	SetContent( final	IFacebookFeedContent	content )
	{
		if( null != content )
		{
			_content	=	content;
		}
	}
	
	public	void	SetComments( final	IFacebookComments	comments )
	{
		if( null != comments )
		{
			_comments	=	comments;
		}
	}
	
	@Override
	public IFacebookFeedContent GetContent() {
		return _content;
	}
	

	@Override
	public IFacebookComments GetComments() {
		return	_comments;
	}
	
	public void set_type( final String type ) 
	{
		if( null != type )
		{
			_type = type;
		}
	}
	
	public	String	get_type()
	{
		return _type;
	}
	
	public String GetType() 
	{
		return	get_type();
	}

	@Override
	public boolean isContaining( final String word ) 
	{
		if ( ( ( ISearchableContent )_content).isContaining( word ) )
		{
			return	true;
		}
		return	false;
	}

}
