package com.sones.businessLogic.GeoNames;

import com.sones.businessLogic.GeoNames.Locations.IGeoNamesLocation;
import com.sones.businessLogic.GeoNames.Locations.IGeoNamesLocations;

/**
 * Provides methods and properties for accessing GeoNames information.
 * 
 * @author Savramis	Sartios
 *
 */
public interface IGeoNamesClient 
{
	/**
	 * Searching in GeoNames for the location name. 
	 * @param locationName
	 * @return true if the location name belongs to a real location and false if it does not.
	 */
	public	boolean	IsLocation(	final String locationName );
	
	/**
	 * Searching in GeoNames for the location name and creates an {@link IGeoNamesLocation}
	 * @param locationName
	 * @return Location from Geo Names.
	 */
	public	IGeoNamesLocation	GetLocation( final String locationName );
	
	/**
	 * Searching in GeoNames for the location name and creates an {@link IGeoNamesLocations}
	 * @param locationName
	 * @return Location from Geo Names.
	 */
	public	IGeoNamesLocations	GetLocations( final String locationName );
	
	/**
	 * Searching in GeoNames for the location name and creates an {@link IGeoNamesLocations}
	 * @param locationName
	 * @return Location from Geo Names.
	 */
	public	IGeoNamesLocations	GetLocationByName( final String locationName );
	
}
