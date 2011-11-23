package com.sones.businessLogic.CapitalWordSpotter.CapitalWords;

/**
 * Provides methods and properties for accessing a capital's word content.
 * 
 * @author Savramis Sartios
 *
 */
public class CapitalWord	implements	ICapitalWord
{

	/**
	 * The value of the word.
	 */
	private	String	_wordValue;
	
	/**
	 * Does nothing
	 */
	public	CapitalWord()
	{
	}
	
	/**
	 * Assigns the value of the parameter to word value.
	 * @param wordValue
	 */
	public	CapitalWord( final String wordValue )
	{
		_wordValue	=	wordValue;
	}

	/**
	 * @return	value of the word.
	 */
	@Override
	public String GetValue() 
	{
		return	_wordValue;
	}
}
