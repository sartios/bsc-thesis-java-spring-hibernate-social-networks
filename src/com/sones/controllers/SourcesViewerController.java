package com.sones.controllers;

import java.awt.Event;
import java.awt.event.KeyEvent;

import com.sones.businessLogic.Facebook.FacebookSearchingList;
import com.sones.dao.FacebookFriendDao;
import com.sones.dao.FacebookGroupDao;
import com.sones.dao.FacebookSearchingListDao;
import com.sones.ui.SourcesViewer;

public class SourcesViewerController {

	private SourcesViewer view_=null;
	private FacebookSearchingList sources_=null;
	
	public SourcesViewerController(){
		view_ = new SourcesViewer();
		init();
	}
	
	public void setSources(final FacebookSearchingList sources){
		this.sources_ = sources;
	}
	
	private void init(){
		setWindowOpenListener();
		setWindowKeyPressedListener();
	}
	
	private void setWindowOpenListener(){
		view_.setWindowOpenListener(new java.awt.event.WindowAdapter(){
				public void windowActivated(java.awt.event.WindowEvent e) {
					if(null!=sources_ ){
						FacebookSearchingListDao dao = new FacebookSearchingListDao();
						view_.setSources(dao.findNames(sources_));
					}
				}
			});
		}

	private void setWindowKeyPressedListener(){
		view_.setWindowKeyPressedListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
					view_.dispose();
				}
			}
		});
	}
	public void showForm(){
		view_.show();
	}
}
