package com.sones.businessLogic.Facebook.FeedManager;

/**
 * Provides methods to set and retrieve data from a facebook status message
 * 
 * @author Savramis Sartios
 *
 */
public interface IFacebookStatusMessage 
{
	/**
	 * @return message of the status message
	 */
	public	String	GetMessage();
	
	/**
	 * Set the message of the status message
	 * @param message
	 */
	public void SetMessage( final String message );
}
