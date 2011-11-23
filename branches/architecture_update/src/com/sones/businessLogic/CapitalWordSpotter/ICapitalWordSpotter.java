package com.sones.businessLogic.CapitalWordSpotter;

import java.util.List;

/**
 * Provides methods for finding words with capital letters.
 * 
 * @author Savramis Sartios
 *
 */
public interface ICapitalWordSpotter 
{
	/**
	 * Finds the words that start with an upper case letter and returns them
	 * @param text
	 * @return collection of words that start with upper case letter
	 */
	public List< String >	GetWordsThatStartWithCapitalLetter( final String text );
}
