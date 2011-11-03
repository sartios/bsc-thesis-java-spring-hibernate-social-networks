package com.sones.businessLogic.Facebook;

import com.sones.buisinessLogic.Facebook.UserManager.IFacebookUserID;
import com.sones.businessLogic.Facebook.FeedManager.IFacebookFeed;
import com.sones.businessLogic.Facebook.FeedManager.IFacebookFeedContent;
import com.sones.businessLogic.Facebook.FeedManager.IFacebookFeeds;
import com.sones.businessLogic.Facebook.FeedManager.CommentManager.IFacebookComments;
import com.sones.businessLogic.UserManager.IApplicationUserID;

public interface IFacebookFeedSaver 
{
	/**
	 * Saves full feeds that application user has download. By full, we mean 
	 * {@link IFacebookFeed} , {@link IFacebookComments} and {@link IFacebookFeedContent}
	 * 
	 * @param userID is {@link IFacebookUserID} of downloader
	 * @param feeds are {@link IFacebookFeeds} that the {@link IFacebookUserID} has download
	 * 
	 * @return true if saving completed
	 */
	public	boolean	SaveUserFacebookFeeds( final IApplicationUserID userID, final IFacebookFeeds feeds );
}
