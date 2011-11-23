package com.sones.businessLogic.CapitalWordSpotter.CapitalWords;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Provides	methods and properties for handling a list of capital words.
 * 
 * @author Savramis Sartios
 *
 */
public class CapitalWords	implements	ICapitalWords
{

	/**
	 * A list of capital words.
	 */
	private	List< ICapitalWord >	_words;
	
	/**
	 * Initializes the list of capital words.
	 */
	public	CapitalWords()
	{
		_words	=	new	ArrayList< ICapitalWord >();
	}
	
	/**
	 * Adds into the list collection an instance of {@link ICapitalWord} implementation,
	 * using as value the string parameter.
	 * @param	words	List of word values.
	 */
	@Override
	public void AddWords(List<String> words) 
	{
		if( 0 < words.size())
		{
			for( int index = 0; index < words.size(); index++)
			{
				ICapitalWord	word	=	new	CapitalWord( words.get( index ) );
				_words.add( word );
			}
		}
	}

	/**
	 * @return	Number of words that start with capital letter.
	 */
	@Override
	public int GetSize()
	{
		return	_words.size();
	}

	/**
	 * @return	Iterator for accessing the collection.
	 */
	@Override
	public	Iterator< ICapitalWord >	GetIterator()
	{
		return	_words.iterator();
	}

}
