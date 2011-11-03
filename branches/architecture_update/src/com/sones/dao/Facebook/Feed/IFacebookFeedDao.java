package com.sones.dao.Facebook.Feed;

import com.sones.businessLogic.Facebook.FeedManager.IFacebookFeed;

public	interface	IFacebookFeedDao 
{
	public	void	SaveFacebookFeed( final	IFacebookFeed	feed );
	public	IFacebookPersistableFeed	GetFeed( final String feedID );
}
