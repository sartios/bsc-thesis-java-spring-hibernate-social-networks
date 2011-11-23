package configuration.setting.manager;

import java.util.NoSuchElementException;
import org.apache.commons.configuration.PropertiesConfiguration;

public abstract class AbstractConfigurationFilePathFinder	implements	IConfigurationFilePathFinder
{

	/**
	 * A property configuration file handler.
	 */
	private	PropertiesConfiguration	_config;
	
	/**
	 * @param 	property of configuration file that keeps paths to other configuration files.
	 * @return 	path to configuration file specified by property. If property is missing, then returns empty string.
	 */
	@Override
	public String GetConfigurationFilePath(String property) 
	{
		String	path	=	new	String("");
		try
		{
			_config.getString( property );
		}
		catch (NoSuchElementException  ex) 
		{
			// TODO: handle exception
		}
		
		return	path;
	}
	
	/**
	 * Initiates property configuration file handler.
	 */
	public	void	initiate()
	{
		_config	=	new PropertiesConfiguration();
	}
	
	/**
	 * Sets exception throwing when property is missing.
	 * @param option if true then on property missing throws exception. If false then it does not.
	 */
	public	void	SetThrowExceptionOnMissing( boolean option )
	{
		_config.setThrowExceptionOnMissing( option );
	}
	
	/**
	 * @return configuration file handler.
	 */
	public	PropertiesConfiguration	GetConfigurationObject()
	{
		return	_config;
	}
	
	/**
	 * Loads configuration file that contains paths for other configuration files.
	 */
	public abstract	void	LoadMainConfigurationFile();
}
