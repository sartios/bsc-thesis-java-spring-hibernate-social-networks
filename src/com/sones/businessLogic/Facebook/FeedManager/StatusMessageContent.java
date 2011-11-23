package com.sones.businessLogic.Facebook.FeedManager;

import com.sones.businessLogic.Searcher.ISearchableContent;

public class StatusMessageContent	extends	AbstractFacebookFeedContent	implements	IFacebookFeedContent, ISearchableContent, IFacebookStatusMessage
{
	private	String	_message;
	
	public	StatusMessageContent()
	{
		_message	=	new	String();
	}

	public	StatusMessageContent( final String message )
	{
		_message	=	message;
	}
	
	public	String	GetMessage() 
	{
		return	_message;
	}

	public void SetMessage( final String message )
	{
		if( null != message )
		{
			_message = message;
		}
		else if( null == message )
		{
			_message="";
		}
	}

	@Override
	public String GetContentType() {
		return "StatusMessage";
	}

	@Override
	public boolean isContaining( final String word) 
	{
		if( IsNotNull( word ) && (false == IsEmpty( word ) ) )
		{
			if( MessageContains( word ) )
			{
				return	true;
			}
		}
		return false;
	}

	private boolean MessageContains( final String word ) 
	{
		return _message.toLowerCase().contains( word );
	}

	private boolean IsEmpty( final String word ) 
	{
		return word.isEmpty();
	}

	private boolean IsNotNull( final Object object ) 
	{
		if( null == object )
		{
			return false;
		}
		return	true;
	}
}

