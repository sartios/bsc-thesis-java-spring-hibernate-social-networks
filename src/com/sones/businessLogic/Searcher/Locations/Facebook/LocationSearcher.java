package com.sones.businessLogic.Searcher.Locations.Facebook;

import com.sones.businessLogic.Searcher.ISearcher;
import com.sones.businessLogic.Searcher.Locations.ILocationSearcher;
import com.sones.businessLogic.UserManager.IApplicationUserID;

public class LocationSearcher	implements	ISearcher
{
	private	ILocationSearcher	_searcher;
	private	IApplicationUserID	_userID;
	
	public	LocationSearcher()
	{
		_searcher	=	new	FacebookLocationSearcher();
	}
	
	@Override
	public boolean Search() 
	{
		_searcher.SearchIntoFeedsForLocations(_userID);
		return true;
	}

	@Override
	public void SetApplicationUser(IApplicationUserID userID) 
	{
		_userID	=	userID;
	}
	
}
