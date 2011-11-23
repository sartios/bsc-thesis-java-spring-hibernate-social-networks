package com.sones.businessLogic.Searcher.Locations.Facebook;

import org.junit.Test;

import com.sones.businessLogic.Searcher.Locations.ILocationSearcher;
import com.sones.businessLogic.UserManager.ApplicationUserID;

public class FacebookLocationSearcherTest 
{
	@Test
	public	void	SearchIntoFeedsForLocationsTest()
	{
		ILocationSearcher	_searcher	=	new	FacebookLocationSearcher();
		_searcher.SearchIntoFeedsForLocations(new ApplicationUserID("1"));
	}
}
