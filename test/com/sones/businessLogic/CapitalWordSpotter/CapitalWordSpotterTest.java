package com.sones.businessLogic.CapitalWordSpotter;

import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sones.businessLogic.CapitalWordSpotter.CapitalWordSpotter;
import com.sones.businessLogic.CapitalWordSpotter.ICapitalWordSpotter;
import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Contents.CapitalizedFacebookLinkTest;

import static	org.junit.Assert.*;

/**
 * Provides methods for testing the {@link #CapitalWordSpotter}
 * 
 * @author Savramis Sartios
 *
 */
public class CapitalWordSpotterTest 
{
	/**
	 * An implementation of the {@link #ICapitalWordSpotter}
	 */
	private	ICapitalWordSpotter	_capitaWordSpotter;
	
	/**
	 * Provides methods to access the configuration file
	 */
	private	PropertiesConfiguration	_config;
	
	/**
	 * Provides methods to log messages
	 */
	private	Logger	_logger;
	
	/**
	 * Initializes the configuration file accessor and the logger
	 */
	public CapitalWordSpotterTest() 
	{
		_logger	=	Logger.getLogger( CapitalizedFacebookLinkTest.class );
		try 
		{
			_config	=	new	PropertiesConfiguration("com/sones/businessLogic/CapitalWordSpotter/CapitalWordSpotterTest.properties");
		} 
		catch (ConfigurationException e) 
		{
			_logger.error( e.getMessage() );
		}
	}

	/**
	 * Initializes the capital word spotter
	 */
	@Before
	public	void	SetUp()
	{
		_capitaWordSpotter	=	new	CapitalWordSpotter();
	}
	
	/**
	 * Cleans up the capital word spotter
	 */
	@After
	public	void	TearDown()
	{
		_capitaWordSpotter	=	null;
	}
	
	/**
	 * Tests if the capital word spotter, spots words that start with capital letter
	 */
	@Test
	public	void	GetWordsThatStartWithCapitalLetter_TextHasWordsWithCapitalAtStart_Test()
	{
		String	text	=	_config.getString( "start_with_capital" );
		List< String > words	=	_capitaWordSpotter.GetWordsThatStartWithCapitalLetter( text );
		assertEquals( words.size(), _config.getInt( "start_with_capital_result" ) );
	}
	
	/**
	 * Tests if the capital word spotter doesn't spot words that start with lower case letters
	 * but have somewhere an upper case letter.
	 */
	@Test
	public	void	GetWordsThatStartWithCapitalLetter_TextHasWordsWithCapitalInTheMiddle_Test()
	{
		String	text	=	_config.getString( "capital_in_the_middle" );
		List< String > words	=	_capitaWordSpotter.GetWordsThatStartWithCapitalLetter( text );
		assertEquals( words.size(), 0 );
	}

}
