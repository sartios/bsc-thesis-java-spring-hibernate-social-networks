package com.sones.businessLogic.Facebook;

import com.sones.buisinessLogic.Facebook.UserManager.IFacebookToken;
import com.sones.buisinessLogic.Facebook.UserManager.IFacebookUserID;
import com.sones.businessLogic.Facebook.DateManager.IFacebookDate;
import com.sones.businessLogic.Facebook.FeedManager.IFacebookFeeds;

/**
 * Facebook feed retriever download facebook feeds, based on user preferences
 * 
 * @author Sartios
 *
 */
public interface IFacebookFeedRetriever {

	/**
	 * Download feeds from facebook.
	 * @param facebookUserID
	 * @param facebookToken
	 * @param facebookDate
	 * @return facebook feeds
	 */
	public	IFacebookFeeds	DownloadFeeds( final IFacebookUserID facebookUserID, final	IFacebookToken	facebookToken,	final	IFacebookDate	facebookDate);
}
