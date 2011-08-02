package com.sones.businessLogic.Facebook.Rest;

import com.sones.businessLogic.Facebook.Feed;
import com.sones.businessLogic.Facebook.Feeds.FacebookFeedList;
import com.sones.businessLogic.Facebook.Source.FacebookFriend;
import com.sones.businessLogic.Facebook.Source.FacebookFriendList;
import com.sones.businessLogic.Facebook.Source.FacebookGroupList;

public interface IFacebookRestHandler {
	
	/**
	 * Downloads the feeds from the specific date and after
	 * 
	 * @param userID
	 * @param token
	 * @param date
	 * @return feeds of the user, after the specified date
	 */
	public FacebookFeedList getFeedsSinceDate(final String userID,final String token,final String date);
	
	/**
	 * Downloads the friend list of the user who token belongs to.
	 * @param token
	 * @return friends of a user
	 */
	public FacebookFriendList getFriendList(final String token);
	
	/**
	 * Downloads the group list of the user who belongs the userID
	 * @param userID
	 * @param token
	 * @return groups of a user
	 */
	public FacebookGroupList getGroupList(final String userID,final String token);

	/**
	 * Downloads the last 25 feeds of a user
	 * @param userID
	 * @param token
	 * @return feeds the last 25
	 */
	public FacebookFeedList getFeeds(final String userID,final String token);
	
	/**
	 * Download a feed with its content, specified by the feed ID
	 * @param feedID
	 * @param token
	 * @return feed with its content
	 */
	public Feed getFeed(final String feedID,final String token);
	
}
