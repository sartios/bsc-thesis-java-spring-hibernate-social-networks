package com.sones.controllers;

import java.io.IOException;

import com.sones.businessLogic.Feed;
import com.sones.businessLogic.FeedList;
import com.sones.businessLogic.Link;
import com.sones.businessLogic.StatusMessage;
import com.sones.businessLogic.Facebook.FacebookFriend;
import com.sones.businessLogic.Facebook.FacebookRestHandler;
import com.sones.dao.FacebookFriendDao;
import com.sones.dao.FeedDao;
import com.sones.ui.LinkViewer;

public class LinkViewerController implements DisplayableFeedController{

	private LinkViewer view_;
	private String feedURL_;
	
	public LinkViewerController(final Feed feed){
		view_ = new LinkViewer();
		this.setFeed(feed);
		init();
	}
	
	public void setFeed(final Feed feed){
		view_.setLinkPhoto(((Link)feed).getPhotoURL());
		
		FacebookFriendDao dao = new FacebookFriendDao();
		FacebookFriend friend = dao.find(((Link)feed).getFromId_());
		if(null==friend){
			FacebookRestHandler handler = new FacebookRestHandler();
			friend=handler.getFacebookUser(((StatusMessage)feed).getFromId_());
		}
		view_.setCreatorName(friend.getName());
		view_.setCreationDate(((Link)feed).getCreatedTime_());
		view_.setLinkDescription(((Link)feed).getDescription());
		feedURL_ = ((Link)feed).getLinkURL();
	}
	
	@SuppressWarnings("deprecation")
	public void showFeed(){
		view_.show();
	}
	
	private void init(){
		setListenerToGoLinkButton();
	}
	
	private void setListenerToGoLinkButton(){
		view_.setListenerToGoLinkButton(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				try
				{
				Process p=Runtime.getRuntime().exec("cmd /c start "+feedURL_);
				}
				catch(IOException e1) {System.out.println(e1);}
			}
		});
}
}
