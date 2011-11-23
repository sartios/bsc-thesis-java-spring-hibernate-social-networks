package com.sones.businessLogic.GeoNames.Locations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a list of {@link IGeoNamesLocation} locations.
 * 
 * @author Savramis	Sartios
 *
 */
public class GeoNamesLocations	implements	IGeoNamesLocations
{
	/**
	 * A list of {@link IGeoNamesLocation} locations.
	 */
	private	List< IGeoNamesLocation >	_locations;

	/**
	 * Initiates a list of {@link IGeoNamesLocation} locations.
	 */
	public	GeoNamesLocations()
	{
		_locations	=	new	ArrayList<IGeoNamesLocation>();
	}
	
	@Override
	public void AddLocation(IGeoNamesLocation location)
	{
		_locations.add( location );
	}

	@Override
	public Iterator<IGeoNamesLocation> GetIterator() 
	{
		return	_locations.iterator();
	}

	@Override
	public int GetSize() 
	{
		return _locations.size();
	}
}
