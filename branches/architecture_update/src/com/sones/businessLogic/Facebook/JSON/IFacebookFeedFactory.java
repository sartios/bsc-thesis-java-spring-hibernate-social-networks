package com.sones.businessLogic.Facebook.JSON;

import com.sones.businessLogic.Facebook.FeedManager.IFacebookFeed;

public interface IFacebookFeedFactory 
{
	public	IFacebookFeed	GetFeed( final Object feedObject );
}
