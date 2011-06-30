package com.sones.controllers;

import java.util.List;

import com.sones.businessLogic.FeedList;
import com.sones.businessLogic.Facebook.FacebookFriend;
import com.sones.businessLogic.Facebook.FacebookFriendList;
import com.sones.businessLogic.Facebook.FacebookGroup;
import com.sones.businessLogic.Facebook.FacebookGroupList;
import com.sones.businessLogic.Facebook.FacebookRestHandler;
import com.sones.businessLogic.Facebook.FacebookSearchingList;
import com.sones.businessLogic.Facebook.FacebookToken;
import com.sones.controllers.Listeners.AddFacebookUserListener;
import com.sones.dao.FeedDao;
import com.sones.dao.TokenDao;
import com.sones.ui.FeedViewer;
import com.sones.ui.SourcesSelector;

public class SourcesSelectorController {
	
	private SourcesSelector			view_;
	private FacebookSearchingList	sources_;
	private FeedViewerController 	controller_;

	private FacebookFriendList		facebookFriends_;
	private FacebookGroupList		facebookGroups_;
	private FacebookToken			facebookToken_;
	private FacebookRestHandler		handler_;
	
	public SourcesSelectorController(SourcesSelector view,FacebookSearchingList sources){
		TokenDao tokenDao = new TokenDao();
		facebookToken_ = (FacebookToken)tokenDao.findAll().get(0);
		handler_=new FacebookRestHandler();
		view_ = view;
		sources_ = sources;
		controller_ = new FeedViewerController(new FeedViewer());
		initializeFacebookFriendsList();
		initializeFacebookGroupsList();
		addMouseAdapters();
	}
	
	public void initializeFacebookFriendsList(){
		facebookFriends_ = handler_.getFriendList("100000866964787",facebookToken_.getToken());
		List list = facebookFriends_.getUsernames();
		view_.setFacebookFriends(list);
	}
	
	public void initializeFacebookGroupsList(){
		facebookGroups_ = handler_.getGroups("100000866964787", facebookToken_.getToken());
		List list = facebookGroups_.getNamesOfGroups();
		view_.setFacebookGroups(list);
	}
	
	public void updateSources(){
		List list = sources_.getIDs();
		view_.setFacebookSourceList(list);
		
	}
	
	public void addMouseAdapters(){
		
		view_.setListenerToAddFacebookFriendsButton(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				int[] indexes = view_.getSelectedFriends();
				List friends = facebookFriends_.getFriendList();
				for(int i=0;i<indexes.length;i++){
					String currentID = ((FacebookFriend)friends.get(indexes[i])).getId();
					if(sources_.getIDs().contains(currentID)){
						break;
					}
					sources_.addID(currentID);
				}
				updateSources();
				System.out.println(sources_.getIDs().size());
			}
		});
		
		view_.setListenerToDeleteFacebookFriendsButton(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				int[] indexes = view_.getSelectedFriends();
				List friends = facebookFriends_.getFriendList();
				for(int i=0;i<indexes.length;i++){
					String currentID = ((FacebookFriend)friends.get(indexes[i])).getId();
					sources_.deleteID(currentID);
				}
				updateSources();
				System.out.println(sources_.getIDs().size());
			}
		});
		
		view_.setListenerToAddFacebookGroupButton(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				int[] indexes = view_.getSelectedGroups();
				List groups = facebookGroups_.getGroups();
				for(int i=0;i<indexes.length;i++){
					String currentID = ((FacebookGroup)groups.get(indexes[i])).getID();
					if(sources_.getIDs().contains(currentID)){
						break;
					}
					sources_.addID(currentID);
				}
				updateSources();
				System.out.println(sources_.getIDs().size());
			}
		});
		
		view_.setListenerToDeleteFacebookGroupButton(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				int[] indexes = view_.getSelectedGroups();
				List groups =  facebookGroups_.getGroups();
				for(int i=0;i<indexes.length;i++){
					String currentID = ((FacebookGroup)groups.get(indexes[i])).getID();
					sources_.deleteID(currentID);
				}
				updateSources();
				System.out.println(sources_.getIDs().size());
			}
		});
		
	}
	
	private void setSourceDeleteButtonListener(){
		view_.setMouseListenerToDeleteSourcesButton(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				int[] indexes = view_.getSelectedSources();
				//List sources = sources_.
			}
		});
	}
	
	public void showForm(){
		view_.show();
	}
}
