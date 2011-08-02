package com.sones.businessLogic.Facebook.Source;

import com.sones.businessLogic.Facebook.Feeds.FacebookFeedList;

public interface ISource {

	public FacebookFeedList getFeeds(final String token);
	public FacebookFeedList getFeeds(final String typeOfList,final String token);
}
