package com.sones.businessLogic.GeoNames.Locations;

/**
 * A simple implementation of {@link IGeoNamesLocation}
 */
public class GeoNamesLocation	implements	IGeoNamesLocation
{
	/**
	 * A name for the location.
	 */
	private	String	_locationName;
	
	/**
	 * A country for the location.
	 */
	private	String	_country;
	
	
	@Override
	public String GetCountry() 
	{
		return	_country;
	}
	
	@Override
	public String GetLocationName() 
	{
		return	_locationName;
	}
	
	@Override
	public void SetCountry(String country) 
	{
		_country	=	country;
	}
	
	@Override
	public void SetLocationName(String locationName)
	{
		_locationName	=	locationName;
	}
}
