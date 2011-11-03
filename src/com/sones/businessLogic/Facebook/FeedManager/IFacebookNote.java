package com.sones.businessLogic.Facebook.FeedManager;

/**
 * Provides methods for accessing Facebook Note content
 * 
 * @author Savramis Sartios
 *
 */
public interface IFacebookNote 
{
	public String GetSubject();
	public void SetSubject( final String subject );
	
	public String GetMessage();
	public void SetMessage( final String message );
}
