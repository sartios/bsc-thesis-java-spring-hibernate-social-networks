package com.sones.controllers;

import com.sones.businessLogic.Feed;
import com.sones.controllers.DisplayableFeedController;
import com.sones.controllers.PictureViewerController;
import com.sones.controllers.StatusMessageViewerController;

public class FeedViewerFactory {

	private static FeedViewerFactory instance_ = null;
	
	private FeedViewerFactory(){	
	}
	
	public static FeedViewerFactory getInstance(){
		if(instance_==null){
			instance_ = new FeedViewerFactory();
		}
		return instance_;
	}
	
	public DisplayableFeedController getFeedController(final Feed feed){
		if(feed.getType().equals("status")){
			return new StatusMessageViewerController(feed);
		}
		if(feed.getType().equals("photo")){
			return new PictureViewerController(feed);
		}
		if(feed.getType().equals("link")){
			return new LinkViewerController(feed);
		}
		return null;
	}
}
