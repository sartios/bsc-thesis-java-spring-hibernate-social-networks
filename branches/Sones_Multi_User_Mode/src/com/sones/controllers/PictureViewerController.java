package com.sones.controllers;

import com.sones.businessLogic.Feed;
import com.sones.businessLogic.Picture;
import com.sones.businessLogic.StatusMessage;
import com.sones.businessLogic.Facebook.FacebookFriend;
import com.sones.businessLogic.Facebook.FacebookRestHandler;
import com.sones.dao.FacebookFriendDao;
import com.sones.ui.PictureViewer;

public class PictureViewerController implements DisplayableFeedController{
	
	private PictureViewer view_;
	
	public PictureViewerController(final Feed feed){
		view_ = new PictureViewer();
		setFeed(feed);
	}
	
	public void setFeed(final Feed feed){
		view_.setImage(((Picture)feed).getUrl());
		view_.setImageDescription(((Picture)feed).getMessage());
		view_.setCreationDate(((Picture)feed).getCreatedTime_());
		
		FacebookFriendDao dao = new FacebookFriendDao();
		FacebookFriend friend = dao.find(((Picture)feed).getFromId_());
		if(null==friend){
			FacebookRestHandler handler = new FacebookRestHandler();
			friend=handler.getFacebookUser(((StatusMessage)feed).getFromId_());
		}
		view_.setCreatorName(friend.getName());
		dao=null;
	}
	
	@SuppressWarnings("deprecation")
	public void showFeed(){
		view_.show(true);
	}
}
