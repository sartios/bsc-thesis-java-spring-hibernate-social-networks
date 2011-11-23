package com.sones.businessLogic.CapitalWordSpotter.CapitalWords;

import java.util.Iterator;
import java.util.List;

/**
 * Provides methods and properties for handling a Collection of capital words.
 * 
 * @author Savramis Sartios
 *
 */
public interface ICapitalWords 
{
	/**
	 * Adds a list of words into the collection of words
	 * @param words
	 */
	public	void	AddWords( final List< String > words );
	
	/**
	 * Returns the size of the collection.
	 * 
	 * @return number of capital words.
	 */
	public	int		GetSize();
	
	/**
	 * @return	Iterator for accessing the collection.
	 */
	public	Iterator< ICapitalWord >	GetIterator();
}
