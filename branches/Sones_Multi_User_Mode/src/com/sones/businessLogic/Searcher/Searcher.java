package com.sones.businessLogic.Searcher;

import com.sones.businessLogic.Facebook.FeedList;

public class Searcher extends AbstractSearcher{

	private KeywordSearcher keywordSearcher_;
	private CommentTrafficSearcher commentTrafficSearcher_;
	
	public Searcher(){
		keywordSearcher_ = new KeywordSearcher();
		commentTrafficSearcher_ = new CommentTrafficSearcher();
	}
	
	@Override
	public void search() {
		FeedList feeds = super.getFeeds();
		if(null!=feeds){
			keywordSearcher_.search(feeds);
			commentTrafficSearcher_.setFeeds(feeds);
			commentTrafficSearcher_.search();
		}
	}

}
