package com.sones.dao.Facebook.Feed.Contents;

import com.sones.businessLogic.Facebook.FeedManager.IFacebookFeedContent;

public interface IFacebookContentDao 
{
	public	void	SaveContent( final IFacebookFeedContent	content );
	public	IFacebookFeedContent	GetContent( final String feedID );
}
