package com.sones.businessLogic.Facebook.FeedManager;

import java.util.List;

public interface IFacebookFeeds {

	public	void	Add( final	IFacebookFeed	feed);
	public	boolean	isEmpty();
	public	IFacebookFeed	GetFeed( final int index );
	public	int	GetSize();
	public	List< String >	GetFeedIDs();
}
