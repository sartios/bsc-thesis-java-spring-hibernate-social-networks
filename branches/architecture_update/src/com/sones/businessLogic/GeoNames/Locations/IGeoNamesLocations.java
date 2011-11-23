package com.sones.businessLogic.GeoNames.Locations;

import java.util.Iterator;

/**
 * Represents a collection of {@link IGeoNamesLocation} locations.
 * 
 * @author Savramis	Sartios
 *
 */
public interface IGeoNamesLocations 
{
	/**
	 * Adds an {@link IGeoNamesLocation} location into the collection.
	 * @param location to be added.
	 */
	public	void	AddLocation( final IGeoNamesLocation location );
	
	/**
	 * Returs an iterator for accessing the collection's values.
	 * @return {@link Iterator} iterator for the {@link IGeoNamesLocation} location collection.
	 */
	public	Iterator< IGeoNamesLocation >	GetIterator();
	
	public	int	GetSize();
}
