package com.sones.businessLogic.SearchOrganizer;

import com.sones.businessLogic.Facebook.FeedList;

public abstract class AbstractSearcher {

	private FeedList feeds_;
	
	public AbstractSearcher(){
		feeds_ = new FeedList();
	}

	public FeedList getFeeds() {
		return feeds_;
	}

	public void setFeeds(FeedList feeds) {
		if(this.feeds_.isEmpty()){
			feeds_ = feeds;
		}
		else{
			this.feeds_.clearFeeds();
			feeds_=feeds;
		}
	}
	
	public void clearFeeds(){
		feeds_.clearFeeds();
	}
	
	public abstract void search();
}
