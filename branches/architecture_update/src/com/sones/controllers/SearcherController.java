package com.sones.controllers;

import com.sones.businessLogic.AbstractSearcher;
import com.sones.businessLogic.SearcherFactory;
import com.sones.businessLogic.SearchingTimer;
import com.sones.businessLogic.Facebook.FacebookSearchingList;
import com.sones.businessLogic.Facebook.FacebookSearchingManager;
import com.sones.ui.KeywordFeedViewer;
import com.sones.ui.KeywordMaker;
import com.sones.ui.SearcherViewer;
import com.sones.ui.SourcesSelector;
import com.sones.ui.SourcesSelector_old;

public class SearcherController {

	private SearcherViewer view_;
	private KeywordMakerController keywordMaker_ = null;
	private SourceSelectorController sourcesSelector_ = null;
	private FacebookSearchingList sourceList_ = null;
	private SourcesViewerController sourceViewer_ = null;
	private KeywordFeedViewerController keywordsAndFeeds = null;
	private FacebookSearchingManager facebookManager=null;
	private SearchingTimer timer=null;
	private FeedsWithCommentTrafficViewerController feedsWithCommentTrafficCntr = null;
	
	public SearcherController(final SearcherViewer view){
		this.view_ = view;
		sourceList_ = new FacebookSearchingList();
		init();
		
	}
	
	private void init(){
		setCreateKeywordListener();
		setViewSelectedSourcesMenuItemListener();
		setCreateSourceListListener();
		setSearchingWindowSliderListener();
		setFeedsAndKeywordsMenuItemListener();
		setSearchButtonListener();
		setViewHotFeedsMenuItemListener();
	}
	
	/**
	 * Set create keywords menu item listener
	 */
	private void setCreateKeywordListener(){
		view_.setCreateKeywordsMenuItemListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				keywordMaker_ = new KeywordMakerController(new KeywordMaker());
				keywordMaker_.showForm();
			}
		});
	}
	
	/**
	 * Set create keywords menu item listener
	 */
	private void setViewSelectedSourcesMenuItemListener(){
		view_.setViewSelectedSourcesMenuItemListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				if(sourceViewer_==null){
					sourceViewer_ = new SourcesViewerController();
				}
				sourceViewer_.setSources(sourceList_);
				sourceViewer_.showForm();
			}
		});
	}
	
	/**
	 * Set create keywords menu item listener
	 */
	private void setCreateSourceListListener(){
		view_.setCreateSourceListMenuItemListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				sourcesSelector_ = new SourceSelectorController();
				sourcesSelector_.setSourcesList(sourceList_);
				sourcesSelector_.showForm();
			}
		});
	}
	
	private void setSearchingWindowSliderListener(){
		view_.setSearchingWindowSliderListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent e) {
				int value = view_.getSearchingWindowValue();
				int second = value%60;
				int minute = value/60;
				view_.setSearchingWindowValue(minute,second);
			}
		});
	}
	
	private void setFeedsAndKeywordsMenuItemListener(){
		view_.setFeedsAndKeywordsMenuItemListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				if(keywordsAndFeeds==null){
					keywordsAndFeeds = new KeywordFeedViewerController(new KeywordFeedViewer());
				}
				
				keywordsAndFeeds.showForm();
			}
		});
	}
	
	private void setViewHotFeedsMenuItemListener(){
		view_.setViewHotFeedsMenuItemListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				if(feedsWithCommentTrafficCntr==null){
					feedsWithCommentTrafficCntr = new FeedsWithCommentTrafficViewerController();
				}
				
				feedsWithCommentTrafficCntr.showForm();
			}
		});
	}
	
	private void setSearchButtonListener(){
		this.view_.setSearchButtonMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				timer = new SearchingTimer(getTime());
				timer.setFacebookSearchingManager(getSearchingManager());
				timer.start();
			}
		});
	}
	
	private FacebookSearchingManager getSearchingManager(){
		facebookManager = new FacebookSearchingManager();
		facebookManager.setSources(sourceList_);
		facebookManager.setSearcher(getSearcher());
		return facebookManager;
	}
	
	private AbstractSearcher getSearcher(){
		String type = view_.getSearchingOptions();
		System.out.println(type);
		return SearcherFactory.getInstance().getSearcher(type);
	}
	
	private int getTime(){
		int value = view_.getSearchingWindowValue();
		int second = value%60;
		int minute = value/60;
		int time = second*1000 + minute*60000;
		return time;
	}
	
	/**
	 * Show form
	 */
	@SuppressWarnings("deprecation")
	public void showForm(){
		view_.show();
	}
}
