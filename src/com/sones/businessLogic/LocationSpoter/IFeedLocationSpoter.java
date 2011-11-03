package com.sones.businessLogic.LocationSpoter;

import com.sones.businessLogic.LocationSpoter.CapitalizedFeeds.ICapitalizedFeeds;
import com.sones.businessLogic.LocationSpoter.LocatedFeeds.ILocatedFeeds;

public interface IFeedLocationSpoter 
{
	/**
	 * Returns the feeds with the locations that they refer to
	 * @param feeds that contain capital letters
	 * @return Feeds related to locations
	 */
	public	ILocatedFeeds	GetFeedsWithLocations( final ICapitalizedFeeds feeds );
}
