package com.sones.businessLogic.Searcher.Facebook;

import com.sones.businessLogic.Searcher.ISearchableFeed;

public interface IFacebookFeedIntegrator 
{
	public	ISearchableFeed	GetFeed( final String feedID );
}
