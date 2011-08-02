package com.sones.businessLogic;

import com.sones.businessLogic.Facebook.Feeds.FacebookFeedList;
import com.sones.businessLogic.KeywordManager.KeywordList;

public interface SocialNetworkUser {

	public KeywordList getKeywords(final String from);
	public FacebookFeedList getNextSourceFeeds();
	public FacebookFeedList getNextSourceFeeds(final String typeOfList);
	public boolean hasMoreSources();
	public void resetSources();
}
