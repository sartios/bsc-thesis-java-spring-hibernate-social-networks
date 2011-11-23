package com.sones.businessLogic.Searcher.Locations.Facebook;

import java.util.Calendar;

import com.sones.businessLogic.LocationSpoter.FeedLocationSpotter;
import com.sones.businessLogic.LocationSpoter.IFeedLocationSpotter;
import com.sones.businessLogic.LocationSpoter.LocatedFeeds.ILocatedFeeds;
import com.sones.businessLogic.Searcher.Locations.ILocationFeedPersister;
import com.sones.businessLogic.Searcher.Locations.ILocationSearchDateProvider;
import com.sones.businessLogic.Searcher.Locations.ILocationSearcher;
import com.sones.businessLogic.UserManager.IApplicationUserID;
import com.sones.dao.FeeLocations.Facebook.FacebookFeedLocationsDao;
import com.sones.dao.Searcher.Locations.Facebook.LocationSearchDateDao;

public class FacebookLocationSearcher	implements	ILocationSearcher
{
	private	IFeedLocationSpotter	_locationSpotter;
	private	ILocationFeedPersister	_persister;
	private	ILocationSearchDateProvider	_searchDateProvider;
	
	public	FacebookLocationSearcher()
	{
		_locationSpotter	=	new	FeedLocationSpotter();
		_persister	=	new	FacebookFeedLocationsDao();
		_searchDateProvider	=	new	LocationSearchDateDao();
	}

	@Override
	public void SearchIntoFeedsForLocations(IApplicationUserID userID) 
	{
		String lastSearch	=	_searchDateProvider.GetDateOfLastSearch(userID);
		String	now	=	Calendar.getInstance().getTime().toString();
		ILocatedFeeds	feeds	=	_locationSpotter.GetFeedsWithLocations(lastSearch,now);
		_persister.SaveFeedsWithLocations(feeds);
		_searchDateProvider.SaveDateOfCurrentSearch(now,userID);
	}

}
