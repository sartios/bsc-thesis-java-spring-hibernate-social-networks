package com.sones.dao.Facebook.Sources;

import com.sones.businessLogic.Facebook.FeedSources.IFacebookSources;
import com.sones.businessLogic.UserManager.IApplicationUserID;

/**
 * Interface for persisting application users' sources {@link IFacebookSources}.
 * 
 * @author Savramis
 *
 */
public interface IUserFacebookSourcesDao 
{
	/**
	 * Retrieves {@link IFacebookUserID} user's defined sources
	 * from which wants feeds.
	 * 
	 * @param userID {@link IFacebookUserID}
	 * @return {@link IFacebookSources}
	 */
	public	IFacebookSources	GetApplicationUserSources( final IApplicationUserID userID );
}
