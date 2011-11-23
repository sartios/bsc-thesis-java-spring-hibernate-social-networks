package com.sones.businessLogic.LocationSpoter.Locations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Locations	implements	ILocations
{

	/**
	 * A list of {@link ILocation}
	 */
	private	List< ILocation >	_locations;
	
	/**
	 * Initiates the objects.
	 */
	public	Locations()
	{
		_locations	=	new	ArrayList<ILocation>();
	}
	
	@Override
	public void AddLocation(ILocation location) 
	{
		_locations.add( location );
	}

	@Override
	public Iterator< ILocation > GetIterator() 
	{
		return	_locations.iterator();
	}

	@Override
	public void AddLocations(ILocations locations) 
	{
		if( null != locations )
		{
			Iterator< ILocation >	locationIt	=	locations.GetIterator();
			while(locationIt.hasNext())
			{
				AddLocation(locationIt.next());
			}
		}
	}

}
