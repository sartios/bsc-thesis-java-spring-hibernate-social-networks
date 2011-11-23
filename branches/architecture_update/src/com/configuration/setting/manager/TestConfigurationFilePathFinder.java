package configuration.setting.manager;

import org.apache.commons.configuration.ConfigurationException;

/**
 * Provides methods and properties for accessing the paths of configuration
 * files that are used into unit tests.
 * 
 * @author Savramis Sartios
 *
 */
public class TestConfigurationFilePathFinder	extends	AbstractConfigurationFilePathFinder
{
	/**
	 * Initiates property configuration file handler.
	 */
	public	TestConfigurationFilePathFinder()
	{
		super.initiate();
		LoadMainConfigurationFile();
	}
	
	/**
	 * Loads Test configuration file that keeps paths for configurations files which are used into tests.
	 */
	@Override
	public void LoadMainConfigurationFile() 
	{
		try 
		{
			GetConfigurationObject().load("config/ConfigurationFilesIndex/TestConfigurationFiles.properties");
		} 
		catch (ConfigurationException e) 
		{
			e.printStackTrace();
		}
	}

}
