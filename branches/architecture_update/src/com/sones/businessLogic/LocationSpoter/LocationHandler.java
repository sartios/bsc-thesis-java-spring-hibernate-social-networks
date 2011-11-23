package com.sones.businessLogic.LocationSpoter;

import org.apache.log4j.Logger;

import com.sones.businessLogic.GeoNames.GeoNamesClient;
import com.sones.businessLogic.GeoNames.IGeoNamesClient;
import com.sones.businessLogic.GeoNames.Locations.IGeoNamesLocation;
import com.sones.businessLogic.GeoNames.Locations.IGeoNamesLocations;
import com.sones.businessLogic.LocationSpoter.Locations.ILocation;
import com.sones.businessLogic.LocationSpoter.Locations.ILocations;
import com.sones.businessLogic.LocationSpoter.Locations.Location;
import com.sones.businessLogic.LocationSpoter.Locations.Locations;

/**
 * Provides methods for finding real locations using GeoNames.
 * 
 * @author Savramis	Sartios
 *
 */
public class LocationHandler	implements	ILocationHandler
{
	private	Logger	_logger	=	Logger.getLogger(LocationHandler.class);
	
	/**
	 * Handles communication with GeoNames.
	 */
	private	IGeoNamesClient	_geoNamesClient;
	
	/**
	 * Initiates GeoNames client.
	 */
	public	LocationHandler()
	{
		_geoNamesClient	=	new	GeoNamesClient();
	}

	@Override
	public ILocation GetLocation(String locationName)
	{
		IGeoNamesLocation	_geoNamesLocation	=	null;
		try
		{
			IGeoNamesLocations	locations	=	_geoNamesClient.GetLocations( locationName );
			_logger.warn("There are "+locations.GetSize() +" locations with the name "+locationName);
			if( locations.GetIterator().hasNext() )
			{
				_geoNamesLocation	=	locations.GetIterator().next();
			}
		}
		catch (NullPointerException ex)
		{
			_logger.error(ex.getMessage());
		}
		
		return	CastToSpotterLocation( _geoNamesLocation );
	}

	@Override
	public boolean IsLocation(String locationName)
	{
		return	_geoNamesClient.IsLocation( locationName );
	}
	
	private	ILocation	CastToSpotterLocation( final IGeoNamesLocation geoNamesLocation )
	{
		ILocation	location	=	new	Location();
		if( null != geoNamesLocation )
		{
			location.SetLocationName( geoNamesLocation.GetLocationName() );
		}
		else	if ( null == geoNamesLocation )
		{
			_logger.warn("There is not a location with this name.");
		}
		return	location;
	}

	@Override
	public ILocations GetLocations(String locationName)
	{
		IGeoNamesLocation	_geoNamesLocation	=	null;
		ILocations	location	=	new	Locations();
		try
		{
			IGeoNamesLocations	locations	=	_geoNamesClient.GetLocationByName( locationName );
			_logger.warn("There are "+locations.GetSize() +" locations with the name "+locationName);
			while( locations.GetIterator().hasNext() )
			{
				_geoNamesLocation	=	locations.GetIterator().next();
				location.AddLocation(CastToSpotterLocation( _geoNamesLocation ));
			}
		}
		catch (NullPointerException ex)
		{
			_logger.error(ex.getMessage());
		}
		
		return	location;
	}

}
