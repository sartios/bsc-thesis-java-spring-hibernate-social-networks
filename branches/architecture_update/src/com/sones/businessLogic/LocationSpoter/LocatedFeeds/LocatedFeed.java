package com.sones.businessLogic.LocationSpoter.LocatedFeeds;

import java.util.Iterator;

import com.sones.businessLogic.LocationSpoter.Locations.ILocation;
import com.sones.businessLogic.LocationSpoter.Locations.ILocations;
import com.sones.businessLogic.LocationSpoter.Locations.Locations;

/**
 * Provides methods and properties for accessing a feed with the locations it refernces to.
 * 
 * @author Savramis	Sartios
 *
 */
public class LocatedFeed	implements	ILocatedFeed
{
	
	/**
	 * The ID of the feed.
	 */
	private	String	_feedID;
	
	/**
	 * Provides methods and properties for accessing the locations
	 */
	private	ILocations	_locations;

	/**
	 * Initiates the objects.
	 */
	public	LocatedFeed()
	{
		_locations	=	new	Locations();
		_feedID	=	new	String();
	}
	
	/**
	 * Associates the feed with the location.
	 */
	@Override
	public void Add(ILocation location) 
	{
		_locations.AddLocation( location );
	}

	/**
	 * Sets feed's ID.
	 */
	@Override
	public void SetFeedID(String feedID) 
	{
		if( null != feedID )
		{
			_feedID	=	feedID;
		}
	}

	@Override
	public Iterator< ILocation > GetLocationsIterator()
	{
		return	_locations.GetIterator();
	}

	@Override
	public String GetID()
	{
		return	_feedID;
	}

	@Override
	public void AddLocations(ILocations locations)
	{
		_locations.AddLocations(locations);
	}

}
