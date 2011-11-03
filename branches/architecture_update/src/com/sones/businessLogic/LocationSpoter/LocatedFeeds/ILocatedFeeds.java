package com.sones.businessLogic.LocationSpoter.LocatedFeeds;

public interface ILocatedFeeds
{
	/**
	 * Add a location for the feed with the corresponding feeID
	 * @param feedID
	 * @param location {@link ILocation}
	 */
	public void Add( final String feedID, final ILocatedFeeds location);
}
