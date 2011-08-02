package com.sones.businessLogic.Facebook.Source;

import testingUtilities.FeedReader;

import com.sones.businessLogic.Facebook.Feeds.FacebookFeedList;
import com.sones.businessLogic.Facebook.JSON.FacebookJSONHandlerFactory;
import com.sones.businessLogic.Facebook.JSON.IFacebookJsonHandler;

public class FacebookOfflineFriend implements ISource{

	@Override
	public FacebookFeedList getFeeds(String token) {
		return getFeeds();
	}

	@Override
	public FacebookFeedList getFeeds(String typeOfList, String token) {
		return getFeeds();
	}
	
	private FacebookFeedList getFeeds(){
		FeedReader reader = new FeedReader();
		IFacebookJsonHandler handler = FacebookJSONHandlerFactory.getInstance().getFacebookJSONHandler("complete");
		FacebookFeedList feeds=new FacebookFeedList();
		feeds.setFeeds(handler.getFeeds(reader.getFacebookWall()));
		return feeds;
	}

}
