package com.sones.businessLogic.Searcher.Locations;

import com.sones.businessLogic.LocationSpoter.LocatedFeeds.ILocatedFeeds;

public interface ILocationFeedPersister 
{
	public	void	SaveFeedsWithLocations( ILocatedFeeds	feeds );
}
