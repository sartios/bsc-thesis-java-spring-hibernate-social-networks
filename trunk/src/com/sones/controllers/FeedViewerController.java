package com.sones.controllers;

import java.util.List;

import com.sones.businessLogic.Feed;
import com.sones.businessLogic.FeedList;
import com.sones.businessLogic.Facebook.FacebookRestHandler;
import com.sones.businessLogic.Facebook.FacebookSearchingList;
import com.sones.businessLogic.Facebook.FacebookToken;
import com.sones.dao.FeedDao;
import com.sones.dao.TokenDao;
import com.sones.ui.FeedViewer;

public class FeedViewerController {

	private FeedViewer		view_ ;
	private FacebookSearchingList	sources_;
	
	public FeedViewerController(FeedViewer view){
		view_ =  view;
		setListenerToSourceFeedViewButton();
		setListenerToFeedViewerButton();
	}
	
	public FeedViewerController(FeedViewer view, FacebookSearchingList sources){
		setListenerToSourceFeedViewButton();
		setListenerToFeedViewerButton();
		view_= view;
		sources_ = sources;
	}
	
	private void initializeSourcesListBox(){
		List ids = sources_.getIDs();
		view_.setSources(ids);
	}
	
	private void setListenerToSourceFeedViewButton(){
		
		view_.setListenerToSourceFeedViewButton(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				int userID =  view_.getSelectedSource();
				FeedDao dao = new FeedDao();
				FeedList userFeeds = dao.findUserFeeds(String.valueOf(userID));
				view_.setSourceFeeds(userFeeds.getIdFromFeeds());
			}
		});
	}
	
	private void setListenerToFeedViewerButton(){
		view_.setListenerToFeedViewerButton(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				String feedID = view_.getSelectedFeed();
				com.sones.businessLogic.Facebook.FacebookRestHandler handler = new FacebookRestHandler();
				TokenDao dao = new TokenDao();
				FacebookToken facebookToken = (FacebookToken) dao.findAll().get(0);
				Feed feed = handler.getFeed(feedID,facebookToken.getToken());
				view_.viewFeed(feed);
			}
		});
	}
	
	public void showForm(){
		view_.setVisible(true);
	}
	
	public void setSources(FacebookSearchingList sources){
		sources_ = sources;
		initializeSourcesListBox();
	}
}
