package com.sones.businessLogic.Facebook;

import com.sones.buisinessLogic.Facebook.UserManager.IFacebookToken;
import com.sones.businessLogic.UserManager.IApplicationUserID;

/**
 * 
 * @author Savramis	Sartios
 *
 */
public interface IFacebookTokenProvider 
{
	/**
	 * To have access to Facebook feeds, each user needs a Facebook token.
	 * 
	 * @param {@link IFacebookUserID}
	 * @return	{@link IFacebookToken}
	 */
	public	IFacebookToken	GetUserToken(  final IApplicationUserID	applicationUserID );
}
