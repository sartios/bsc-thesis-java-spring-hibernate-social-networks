package com.sones.businessLogic;

public abstract class AbstractSearcher {

	private FeedList feeds_;
	
	public AbstractSearcher(){
		
	}

	public FeedList getFeeds() {
		return feeds_;
	}

	public void setFeeds(final FeedList feeds) {
		feeds_ = feeds;
	}
	
	public abstract void search();
}
