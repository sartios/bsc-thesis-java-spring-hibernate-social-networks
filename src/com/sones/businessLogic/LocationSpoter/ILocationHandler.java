package com.sones.businessLogic.LocationSpoter;

import	com.sones.businessLogic.LocationSpoter.Locations.ILocation;
import com.sones.businessLogic.LocationSpoter.Locations.ILocations;

/**
 * Provides methods for finding real locations.
 * 
 * @author Savramis	Sartios
 *
 */
public interface ILocationHandler 
{
	/**
	 * Searching for the location name. 
	 * @param locationName
	 * @return true if the location name belongs to a real location and false if it does not.
	 */
	public	boolean	IsLocation(	final String locationName );
	
	/**
	 * Searching for the location name and creates a {@link ILocation}
	 * @param locationName
	 * @return Location.
	 */
	public	ILocation	GetLocation( final String locationName );
	
	public	ILocations	GetLocations( final String locationName );
}
