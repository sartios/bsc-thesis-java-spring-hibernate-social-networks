package com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Contents;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;

/**
 * Provides methods to access the configuration file that contains
 * data to be used for the Capitalized facebook content tests.
 * In addition, provides the logger of these tests.
 * 
 * @author Savramis Sartios
 *
 */
public class CapitalizedFacebookContentTest 
{
	/**
	 * Accesses the configuration file
	 */
	private	PropertiesConfiguration	_config;
	
	/**
	 * Loggs the messages
	 */
	private	Logger	_logger;
	
	/**
	 * Initializes the configuration file reader and the logger.
	 * @param clazz the actual class
	 */
	public CapitalizedFacebookContentTest( final Class clazz )
	{
		_logger	=	Logger.getLogger( clazz );
		try 
		{
			_config	=	new	PropertiesConfiguration("com/sones/businessLogic/LocationSpoter/CapitalizedFeeds/Searchable/Contents/CapitalizedFacebookContentTest.properties");
		} 
		catch (ConfigurationException e) 
		{
			_logger.error( e.getMessage() );
		}
	}
	
	/**
	 * @param property of the configuration file
	 * @return value of the property
	 */
	public	String	GetProperty( final String property )
	{
		return	_config.getString(property);
	}
	
	/**
	 * Loggs the message
	 * @param message to be logged
	 */
	public	void	LogError( final String message )
	{
		_logger.error( message );
	}
}
