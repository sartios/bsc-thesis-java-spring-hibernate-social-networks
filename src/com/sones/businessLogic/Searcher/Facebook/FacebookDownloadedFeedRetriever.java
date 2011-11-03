package com.sones.businessLogic.Searcher.Facebook;

import java.util.Iterator;
import java.util.List;

import com.sones.businessLogic.Searcher.ISearchableFeed;
import com.sones.businessLogic.Searcher.ISearchableFeeds;
import com.sones.businessLogic.Searcher.SearchableFeeds;
import com.sones.businessLogic.UserManager.IApplicationUserID;
import com.sones.dao.User.Facebook.Feeds.IUserFacebookFeedsDao;
import com.sones.dao.User.Facebook.Feeds.UserFacebookFeedsDao;

public class FacebookDownloadedFeedRetriever implements	IFacebookDownloadedFeedRetriever
{
	private	IUserFacebookFeedsDao	_userFeeds;
	private	IFacebookFeedIntegrator	_feedIntegrator;
	
	public	FacebookDownloadedFeedRetriever()
	{
		_feedIntegrator	=	new	FacebookFeedIntegrator();
		_userFeeds	=	new	UserFacebookFeedsDao();
	}
	
	
	@Override
	public ISearchableFeeds GetFeeds(IApplicationUserID userID) 
	{
		ISearchableFeeds	feeds	=	new SearchableFeeds();
		List< String >	feedIDs	=	_userFeeds.GetUserFacebookFeeds( userID.GetValue() );
		Iterator< String >	feedID	=	feedIDs.iterator();
		for( ; feedID.hasNext(); )
		{
			ISearchableFeed	feed	=	_feedIntegrator.GetFeed( feedID.next() );
			feeds.AddFeed( feed );
		}
		return	feeds;
	}
	
}
