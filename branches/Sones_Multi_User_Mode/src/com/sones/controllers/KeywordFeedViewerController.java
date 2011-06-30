package com.sones.controllers;

import java.util.ArrayList;
import java.util.List;

import com.sones.businessLogic.Feed;
import com.sones.businessLogic.FeedList;
import com.sones.businessLogic.Keyword;
import com.sones.businessLogic.Facebook.FacebookRestHandler;
import com.sones.dao.FeedDao;
import com.sones.dao.KeywordDao;
import com.sones.ui.KeywordFeedViewer;

public class KeywordFeedViewerController {

	private KeywordFeedViewer view_;
	private KeywordDao keywordDao_;
	private List<Keyword> keywords_;
	private DisplayableFeedController controller_=null;
	
	public KeywordFeedViewerController(KeywordFeedViewer view){
		view_=view;
		keywordDao_ = new KeywordDao();
		initializeKeywords();
		initializeViewFeedsButton();
		initializeViewButton();
	}
	
	private void initializeKeywords(){
		keywords_ = keywordDao_.findAll();
		List<String>	keywordsValues = new ArrayList<String>();
		
		for(int i=0;i<keywords_.size();i++){
			keywordsValues.add(keywords_.get(i).getValue());
		}
		this.view_.setKeywordList(keywordsValues);
	}
	
	private void initializeViewFeedsButton(){
		
		view_.setListernerToViewFeedsButton(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				int index = view_.getSelectedKeywordIndex();
				String keywordID = keywords_.get(index).getValue();
				Keyword keyword = keywordDao_.getKeywordWithFeeds(keywordID);
				view_.setKeywordsFeeds(keyword.getFeedsIds());
			}
		});
	}
	
	private void initializeViewButton(){
		
		view_.setListernerToViewButton(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				int index = view_.getSelectedKeywordIndex();
				String feedID = view_.getSelectedFeed();
				FacebookRestHandler handler = new FacebookRestHandler();
				Feed feed = handler.getFeed(feedID);
				controller_ = FeedViewerFactory.getInstance().getFeedController(feed);
				controller_.showFeed();
			}
		});
	}
	
	@SuppressWarnings("deprecation")
	public void showForm(){
		view_.show();
	}
}
