package com.sones.businessLogic.GeoNames;

import java.util.Iterator;

import org.apache.commons.lang.NotImplementedException;
import org.geonames.Toponym;
import org.geonames.ToponymSearchCriteria;
import org.geonames.ToponymSearchResult;
import org.geonames.WebService;

import com.sones.businessLogic.GeoNames.Locations.GeoNamesLocation;
import com.sones.businessLogic.GeoNames.Locations.GeoNamesLocations;
import com.sones.businessLogic.GeoNames.Locations.IGeoNamesLocation;
import com.sones.businessLogic.GeoNames.Locations.IGeoNamesLocations;

/**
 * Provides methods and properties for using Geo Names API.
 * 
 * @author Savramis Sartios
 *
 */
public class GeoNamesClient	implements	IGeoNamesClient
{

	/**
	 * Returns the locations that exist with the location name.
	 * @param	locationName	the name of the location.
	 * @return	{@link IGeoNamesLocations}	locations.
	 */
	@Override
	public IGeoNamesLocations GetLocations(String locationName) 
	{
		ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria();
		searchCriteria.setQ(locationName);
		ToponymSearchResult searchResult	=	null;
		try 
		{
			WebService.setUserName("sartios");
			searchResult = WebService.search(searchCriteria);
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		IGeoNamesLocations	locations	=	null;
		if( null != searchResult )
		{
			locations	=	new	GeoNamesLocations();
			Iterator< Toponym >	iterator	=	searchResult.getToponyms().iterator();
			for( ;iterator.hasNext(); )
			{
				IGeoNamesLocation	location	=	new	GeoNamesLocation();
				Toponym	toponym	=	iterator.next();
				location.SetLocationName( toponym.getName() );
				location.SetCountry( toponym.getCountryName() );
				locations.AddLocation( location );
			}
		}
		else	if (null == searchResult)
		{
			throw	new	NullPointerException("There are no locations with this name");
		}
		return	locations;
	}

	/**
	 * Not implemented yet.
	 */
	@Override
	public boolean IsLocation(String locationName)
	{
		return	true;
	}

	@Override
	public IGeoNamesLocations GetLocationByName(String locationName) 
	{
		ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria();
		searchCriteria.setName(locationName);
		ToponymSearchResult searchResult	=	null;
		try 
		{
			WebService.setUserName("sartios");
			searchResult = WebService.search(searchCriteria);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		IGeoNamesLocations	locations	=	null;
		if( null != searchResult )
		{
			locations	=	new	GeoNamesLocations();
			Iterator< Toponym >	iterator	=	searchResult.getToponyms().iterator();
			for( ;iterator.hasNext(); )
			{
				IGeoNamesLocation	location	=	new	GeoNamesLocation();
				Toponym	toponym	=	iterator.next();
				location.SetLocationName( toponym.getName() );
				location.SetCountry( toponym.getCountryName() );
				locations.AddLocation( location );
			}
		}
		else	if (null == searchResult)
		{
			throw	new	NullPointerException("There are no locations with this name");
		}
		return	locations;
	}

	@Override
	public IGeoNamesLocation GetLocation(String locationName)
	{
		throw	new	NotImplementedException("GetLocation() is not implemented");
	}

}
