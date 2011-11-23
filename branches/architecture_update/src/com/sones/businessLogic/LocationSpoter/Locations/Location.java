package com.sones.businessLogic.LocationSpoter.Locations;

import org.apache.log4j.Logger;

public class Location	implements	ILocation
{

	private	String	_locationName;
	
	public	Location()
	{
	}
	
	@Override
	public	String	GetLocationName() 
	{
		return	_locationName;
	}

	@Override
	public	void	SetLocationName(String locationName) 
	{
		if( null != locationName)
		{
			_locationName	=	locationName;
		}
		else
		{
			Logger.getLogger(Location.class).warn("Empty location name");
		}
	}

}
