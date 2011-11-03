package com.sones.businessLogic.Searcher;

import com.sones.businessLogic.Facebook.FeedManager.IFacebookFeed;

public interface ISearchableFeed	extends	IFacebookFeed
{
	public	boolean	isContaining( final	String	word );
	public	String	GetID();
}
