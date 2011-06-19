package com.sones.businessLogic.Searcher;

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
		try{
			feeds_.clearFeeds();
		}
		catch(NullPointerException ex){}
	}
	
	public abstract void search();
}
