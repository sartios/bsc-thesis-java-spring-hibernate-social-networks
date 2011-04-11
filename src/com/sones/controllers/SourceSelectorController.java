package com.sones.controllers;

import java.util.List;

import com.sones.businessLogic.Facebook.FacebookFriend;
import com.sones.businessLogic.Facebook.FacebookFriendList;
import com.sones.businessLogic.Facebook.FacebookGroup;
import com.sones.businessLogic.Facebook.FacebookGroupList;
import com.sones.businessLogic.Facebook.FacebookRestHandler;
import com.sones.businessLogic.Facebook.FacebookSearchingList;
import com.sones.dao.FacebookFriendDao;
import com.sones.dao.FacebookGroupDao;
import com.sones.ui.SourcesSelector;

public class SourceSelectorController {

	private SourcesSelector view_ =  null;
	private FacebookSearchingList sources_;
	

	public SourceSelectorController(){
		view_ = new SourcesSelector();
		init();
		sources_ = new FacebookSearchingList();
	}
	
	public SourceSelectorController(final SourcesSelector view){
		view_ = view;
		init();
	}
	
	private void init(){
		setLoadFacebookFriendsMenuItemListener();
		setDownloadFacebookFriendsMenuItemListener();
		setLoadFacebookGroupsMenuItemListener();
		setDownloadFacebookGroupsMenuItemListener();
		setAddFacebookFriendButtonListener();
		setAddFacebookGroupButtonListener();
		addDoubleClickEventToFacebookFriendsList();
		addDoubleClickEventToFacebookGroupsList();
	}
	
	private void setLoadFacebookFriendsMenuItemListener(){
		view_.setLoadFacebookFriendsMenuItemListerner(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				FacebookFriendDao friendDao = new FacebookFriendDao();
				List friends = (friendDao.findAll()).getUsernames();
				view_.setFacebookFriendsUsernames(friends);
			}
		});
	}
	
	private void setDownloadFacebookFriendsMenuItemListener(){
		view_.setDownloadFacebookFriendsMenuItemListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				FacebookRestHandler rest = new FacebookRestHandler();
				FacebookFriendList friends = rest.getFriendList();
				FacebookFriendDao dao = new FacebookFriendDao();
				int numberOfFriends = friends.getSize();
				for(int i=0;i<numberOfFriends;i++){
					FacebookFriend friend = friends.getFriendList().get(i);
					dao.saveOrUpdate(friend);
					view_.addFacebookFriend(friend.getName());
				}
			}
		});
	}
	
	private void setLoadFacebookGroupsMenuItemListener(){
		view_.setLoadFacebookGroupsMenuItemListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				FacebookGroupDao groupDao = new FacebookGroupDao();
				List groups = (groupDao.findAll()).getNamesOfGroups();
				view_.setFacebookGroups(groups);
			}
		});
	}
	
	private void setDownloadFacebookGroupsMenuItemListener(){
		view_.setDownloadFacebookGroupsMenuItemListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				FacebookRestHandler rest = new FacebookRestHandler();
				
				FacebookGroupList groups = rest.getGroups("100000866964787");
				FacebookGroupDao dao = new FacebookGroupDao();
				int numberOfFriends = groups.getSize();
				for(int i=0;i<numberOfFriends;i++){
					FacebookGroup group = groups.getGroups().get(i);
					dao.saveOrUpdate(group);
					view_.addFacebookGroup(group.getName());
				}
			}
		});
	}
	
	public void setSourcesList(final FacebookSearchingList sources){
		sources_ = sources;
	}
	
	public FacebookSearchingList getSourcesList(){
		return sources_;
	}
	
	private void setAddFacebookFriendButtonListener(){
		
		view_.setAddFacebookFriendButtonListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				addFriendsToList();
/*				view_.setSelectedSources(sources_.getIDs());
*/			}
		});		
	}
	
	private void setAddFacebookGroupButtonListener(){
		
		view_.setAddFacebookGroupButtonListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				addGroupsToList();
			}
		});		
	}
	
	private void addFriendsToList(){
		FacebookFriendDao dao = new FacebookFriendDao();
		List friends=view_.getFriends();
		for(int i=0;i<friends.size();i++){
			FacebookFriend friend = (FacebookFriend)dao.findByName((String) friends.get(i));
			sources_.addID(friend.getId());
		}	
		view_.setSelectedSources(sources_.getIDs());
	}
	
	private void addGroupsToList(){
		FacebookGroupDao dao = new FacebookGroupDao();
		List groups=view_.getGroups();
		for(int i=0;i<groups.size();i++){
			FacebookGroup group = (FacebookGroup)dao.findByName((String) groups.get(i));
			sources_.addID(group.getId());
		}	
		view_.setSelectedSources(sources_.getIDs());
	}
	
	private void addDoubleClickEventToFacebookFriendsList(){
		view_.addDoubleClickEventToFacebookFriendsList(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				if(2==e.getClickCount()){
					addFriendsToList();
					view_.setSelectedSources(sources_.getIDs());
				}
			}
		});
	}
	
	private void addDoubleClickEventToFacebookGroupsList(){
		view_.addDoubleClickEventToFacebookGroupsList(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				if(2==e.getClickCount()){
					addGroupsToList();
				}
			}
		});
	} 
	
	public void showForm(){
		view_.show();
	}
}
