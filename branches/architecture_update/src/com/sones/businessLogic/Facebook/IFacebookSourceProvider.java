package com.sones.businessLogic.Facebook;

import com.sones.businessLogic.Facebook.FeedSources.IFacebookSources;
import com.sones.businessLogic.UserManager.IApplicationUserID;

/**
 * Provides user sources for downloading feeds
 * 
 * @author Sartios
 *
 */
public interface IFacebookSourceProvider 
{
	public	IFacebookSources	GetUserSources( final	IApplicationUserID	applicationUserID );
}
