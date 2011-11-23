package com.sones.businessLogic.LocationSpoter.Locations;

import java.util.Iterator;

/**
 * Provides methods for handling a collection of {@link ILocation}
 * 
 * @author Savramis	Sartios
 *
 */
public interface ILocations 
{
	/**
	 * Adds a location into collection.
	 * @param location
	 */
	public	void	AddLocation( final ILocation location);
	
	/**
	 * Adds a location into collection.
	 * @param locations
	 */
	public	void	AddLocations( final ILocations	locations);
	
	/**
	 * Returns an Iterator for accessing the locations.
	 * @return {@link Iterator} iterator for accessing the locations.
	 */
	public	Iterator< ILocation >	GetIterator();
}
