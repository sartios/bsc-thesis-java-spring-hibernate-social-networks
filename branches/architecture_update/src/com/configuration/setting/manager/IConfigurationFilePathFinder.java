package configuration.setting.manager;

/**
 * Provides methods for accessing configuration file paths.
 * 
 * @author Savramis Sartios
 *
 */
public interface IConfigurationFilePathFinder 
{
	/**
	 * Reads the main configuration file index, in which exists the paths
	 * for the configuration files that are used and returns the path.
	 * 
	 * @param	Property name for finding configuration file.
	 * @return Path of configuration file.
	 */
	public	String	GetConfigurationFilePath( final String property );
}
