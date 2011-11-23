package com.sones.businessLogic.LocationSpoter.LocatedFeeds;

import java.util.Iterator;

/**
 * Provides methods for handling a collection of {@link ILocatedFeed}
 * 
 * @author Savramis Sartios
 *
 */
public interface ILocatedFeeds
{
	/**
	 * Adds a {@link ILocatedFeed} into the collection.
	 * @param	located feed
	 */
	public void Add( final ILocatedFeed locatedFeed );

	public Iterator<ILocatedFeed> GetIterator();
	
	public	void	AddFeeds(ILocatedFeeds feeds);
}
