package com.sones.businessLogic.Facebook.API;

import com.sones.buisinessLogic.Facebook.UserManager.IFacebookToken;
import com.sones.buisinessLogic.Facebook.UserManager.IFacebookUserID;
import com.sones.businessLogic.Facebook.DateManager.IFacebookDate;

/**
 * Provides a unified interface for accessing Facebook API
 * 
 * @author Sartios
 *
 */
public interface IFacebookAPI {
	
	/**
	 * Facebook API call for retrieving user's feeds
	 * 
	 * @param facebookUserID
	 * @param facebookToken
	 * @param facebookDate
	 * @return string with results of the API call
	 */
	public	String	GetFacebookFeeds( final	IFacebookUserID	facebookUserID, final	IFacebookToken	facebookToken,	final	IFacebookDate	facebookDate);
	

}
