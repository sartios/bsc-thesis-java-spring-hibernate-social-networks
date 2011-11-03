package com.sones.businessLogic.Searcher.Facebook;

import com.sones.businessLogic.Searcher.ISearchableFeeds;
import com.sones.businessLogic.UserManager.IApplicationUserID;

public interface IFacebookDownloadedFeedRetriever 
{
	public	ISearchableFeeds	GetFeeds( final IApplicationUserID userID );
}
