package com.sones.controllers;

import java.awt.event.KeyEvent;

import org.apache.log4j.DailyRollingFileAppender;

import com.sones.businessLogic.Keyword;
import com.sones.dao.KeywordDao;
import com.sones.ui.KeywordMaker;

public class KeywordMakerController {

	private KeywordMaker view_;
	private KeywordDao 	dao_;
	
	public KeywordMakerController(final KeywordMaker view){
		this.view_ = view;
		dao_ = new KeywordDao();
		setAdapterToKeywordSaverButton();
		setAdapterToKeywordTextField();
	}
	
	private void setAdapterToKeywordSaverButton(){
		
		view_.setMouseListenerToSaveButton(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				dao_.saveOrUpdate(new Keyword(view_.getKeywordValue()));
				view_.clearKeywordTextBox();
			}
		});
	}
	
	private void setAdapterToKeywordTextField(){
		
		view_.setKeyboardListenerToKeywordTextField(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent e) {
				if(KeyEvent.VK_ENTER==e.getKeyCode()){
					dao_.saveOrUpdate(new Keyword(view_.getKeywordValue()));
					view_.clearKeywordTextBox();
				}
			}
		});
	}
	
	public void showForm(){
		view_.show();
	}
}
