package com.sones.businessLogic.Facebook;

import com.sones.businessLogic.UserManager.IApplicationUserID;

/**
 * Provides methods and properties for downloading feeds from Facebook.
 * 
 * @author Savramis Sartios
 *
 */
public interface IFacebookDownloader {

	/**
	 * Download Facebook feeds for user {@link IFacebookUserID} based on his preferences
	 */
	public	boolean	DownloadFacebookFeeds( final IApplicationUserID applicationUserID );
	
	public	void	SetApplicationUser( final IApplicationUserID applicationUserID );
}
