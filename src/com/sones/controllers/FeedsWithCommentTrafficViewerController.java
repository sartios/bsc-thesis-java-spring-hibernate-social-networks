package com.sones.controllers;

import java.util.ArrayList;
import java.util.List;

import com.sones.businessLogic.Feed;
import com.sones.businessLogic.Facebook.FacebookRestHandler;
import com.sones.dao.FeedsWithCommentTrafficDao;
import com.sones.ui.FeedsWithCommentTrafficViewer;

public class FeedsWithCommentTrafficViewerController {

	private FeedsWithCommentTrafficViewer view_;
	private DisplayableFeedController feedDisplayer_;
	
	public FeedsWithCommentTrafficViewerController(){
		view_ = new FeedsWithCommentTrafficViewer();
		init();
	}
	
	private void init(){
		initSearchingDates();
		setListenerToSearchingDateSelection();
		addDoubleClickEventToFeedSelection();	
	}
	
	private void initSearchingDates(){
		List<String> searchingDates = new ArrayList<String>();
		FeedsWithCommentTrafficDao dao = new  FeedsWithCommentTrafficDao();
		searchingDates=dao.getSearchingDatesWhereResultsFound();		
		view_.setSearchingDates(searchingDates);
		dao=null;
	}
	
	private void setListenerToSearchingDateSelection(){
		view_.setListenerToSearchingDateSelection(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent e) {
				FeedsWithCommentTrafficDao dao = new  FeedsWithCommentTrafficDao();
				String selectedDate = view_.getSelectedDate();
				List<String> feeds = dao.getFeedListByTime(selectedDate);
				view_.setFeeds(feeds);
				dao=null;
			}
		});
	}
	
	private void addDoubleClickEventToFeedSelection() {
		view_.addDoubleClickEventToFeedSelection(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				if(2==e.getClickCount()){
					String feedID = view_.getSelectedFeed();
					FacebookRestHandler handler = new FacebookRestHandler();
					Feed feed = handler.getFeed(feedID);
					feedDisplayer_ = FeedViewerFactory.getInstance().getFeedController(feed);
					feedDisplayer_.showFeed();
				}
			}
		});
	}
	
	
	@SuppressWarnings("deprecation")
	public void showForm(){
		initSearchingDates();
		view_.show();
	}
	
}
