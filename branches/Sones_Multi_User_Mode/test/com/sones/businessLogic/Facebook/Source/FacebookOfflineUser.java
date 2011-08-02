package com.sones.businessLogic.Facebook.Source;

import com.sones.businessLogic.SocialNetworkUser;
import com.sones.businessLogic.Facebook.Feeds.FacebookFeedList;
import com.sones.businessLogic.KeywordManager.Keyword;
import com.sones.businessLogic.KeywordManager.KeywordList;

public class FacebookOfflineUser implements SocialNetworkUser{
	private KeywordList keywords_;
	private FacebookSourceList sources_;
	private ISource friend_;
	
	public FacebookOfflineUser(){
		keywords_=new KeywordList();
		keywords_.addKeyword(new Keyword("youtube"));	
		friend_=new FacebookOfflineFriend();
		sources_=new FacebookSourceList();
		sources_.addSource(friend_);
	}

	@Override
	public FacebookFeedList getNextSourceFeeds() {
		return sources_.getNext().getFeeds("");
	}

	@Override
	public FacebookFeedList getNextSourceFeeds(String typeOfList) {
		return sources_.getNext().getFeeds("");
	}

	@Override
	public KeywordList getKeywords(String from) {
		return this.keywords_;
	}

	@Override
	public boolean hasMoreSources() {
		return sources_.hasNext();
	}
	
	public void setKeywordsThatDoesNotExist(){
		keywords_ = new KeywordList();
		keywords_.addKeyword(new Keyword("@@@###$$$%%%"));
	}

	@Override
	public void resetSources() {
		this.sources_.reset();
	}

}
