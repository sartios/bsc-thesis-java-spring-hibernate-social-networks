package com.sones.businessLogic.LocationSpoter.LocatedFeeds;

import java.util.Iterator;

import com.sones.businessLogic.LocationSpoter.Locations.ILocation;
import com.sones.businessLogic.LocationSpoter.Locations.ILocations;

/**
 * Provides methods for associating a feed with location.
 * 
 * @author Savramis	Sartios
 *
 */
public interface ILocatedFeed
{
	/**
	 *	Sets the ID of the feed.
	 *	@param	feedID 
	 */
	public	void	SetFeedID( final String feedID );
	
	/**
	 * Add a location for the feed with the corresponding feeID
	 * @param location {@link ILocation}
	 */
	public void Add(final ILocation location);
	
	/**
	 * Returns an iterator to access locations.
	 * @return {@link Iterator} iterator for accessing the locations.
	 */
	public	Iterator< ILocation >	GetLocationsIterator();

	public String GetID();

	public void AddLocations(ILocations locations);
}
