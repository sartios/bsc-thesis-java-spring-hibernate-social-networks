package com.sones.dao.FeeLocations.Facebook;

import org.junit.Test;

import com.sones.businessLogic.LocationSpoter.FeedLocationSpotter;
import com.sones.businessLogic.LocationSpoter.LocatedFeeds.ILocatedFeeds;

public class FacebookFeedLocationsDaoTest 
{

	@Test
	public	void	SaveFeedsWithLocationsTest()
	{
		FeedLocationSpotter	_locationSpotter	=	new	FeedLocationSpotter();
		ILocatedFeeds	feeds	=	_locationSpotter.GetFeedsWithLocations("Mon Nov 14 22:10:20 EET 2011", "Mon Nov 14 22:47:00 EET 2011");
		FacebookFeedLocationsDao	dao	=	new	FacebookFeedLocationsDao();
		dao.SaveFeedsWithLocations(feeds);
	}
}
