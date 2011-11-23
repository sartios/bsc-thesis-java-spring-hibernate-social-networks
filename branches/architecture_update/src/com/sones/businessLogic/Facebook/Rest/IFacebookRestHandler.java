package com.sones.businessLogic.Facebook.Rest;

import com.sones.buisinessLogic.Facebook.UserManager.IFacebookToken;
import com.sones.buisinessLogic.Facebook.UserManager.IFacebookUserID;
import com.sones.businessLogic.Facebook.DateManager.IFacebookDate;

public interface IFacebookRestHandler {
	
	/**
	 * Downloads the feeds from the specific date and after
	 * 
	 * @param userID
	 * @param token
	 * @param date
	 * @return feeds of the user, after the specified date
	 */
	public	String	GetFeedsSinceDate( final IFacebookUserID facebookUserID, final	IFacebookToken facebookToken, final	IFacebookDate facebookDate);
	
	/**
	 * Downloads the friend list of the user who token belongs to.
	 * @param token
	 * @return friends of a user
	 */
	public String getFriendList(final String token);
	
	/**
	 * Downloads the group list of the user who belongs the userID
	 * @param userID
	 * @param token
	 * @return groups of a user
	 */
	public String getGroupList(final String userID,final String token);

	/**
	 * Downloads the last 25 feeds of a user
	 * @param userID
	 * @param token
	 * @return feeds the last 25
	 */
	public String getFeeds(final String userID,final String token);
	
	/**
	 * Download a feed with its content, specified by the feed ID
	 * @param feedID
	 * @param token
	 * @return feed with its content
	 */
	public String getFeed(final String feedID,final String token);
	
}
