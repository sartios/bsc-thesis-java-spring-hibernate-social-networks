package com.sones.businessLogic.Facebook.FeedManager;

public abstract class AbstractFacebookFeedContent {

	private	String	_feedID;
	private	String	_type;
	
	public void SetFeedID( final String feedID ) 
	{
		if( null != feedID )
		{
			_feedID = feedID;
		}
		else if( null != feedID)
		{
			_feedID	=	"";
		}
	}

	public String GetFeedID() 
	{
		return _feedID;
	}

	public void SetType(String type) 
	{
		_type = type;
	}

	public String GetType()
	{
		return _type;
	}
}
