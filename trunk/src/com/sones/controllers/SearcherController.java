package com.sones.controllers;

import com.sones.businessLogic.Facebook.FacebookSearchingList;
import com.sones.ui.KeywordMaker;
import com.sones.ui.Searcher;
import com.sones.ui.SourcesSelector;

public class SearcherController {

	private Searcher view_;
	private KeywordMakerController keywordMaker_ = null;
	private SourcesSelectorController sourcesSelector_ = null;
	private FacebookSearchingList sourceList_ = null;
	
	public SearcherController(final Searcher view){
		this.view_ = view;
		sourceList_ = new FacebookSearchingList();
		setCreateKeywordListener();
		setCreateSourceListListener();
		
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
	private void setCreateSourceListListener(){
		view_.setCreateSourceListMenuItemListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				sourcesSelector_ = new SourcesSelectorController(new SourcesSelector(),sourceList_);
				sourcesSelector_.showForm();
			}
		});
	}
	
	/**
	 * Show form
	 */
	@SuppressWarnings("deprecation")
	public void showForm(){
		view_.show();
	}
}
