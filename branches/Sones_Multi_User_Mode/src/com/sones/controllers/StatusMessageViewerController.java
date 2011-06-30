package com.sones.controllers;

import com.sones.businessLogic.Feed;
import com.sones.businessLogic.StatusMessage;
import com.sones.businessLogic.Facebook.FacebookFriend;
import com.sones.businessLogic.Facebook.FacebookRestHandler;
import com.sones.dao.FacebookFriendDao;
import com.sones.ui.StatusMessageViewer;

public class StatusMessageViewerController implements DisplayableFeedController {

	private StatusMessageViewer viewer;
	
	public StatusMessageViewerController(final Feed feed){
		viewer = new StatusMessageViewer();
		setFeed(feed);
	}
	
	public void setFeed(final Feed feed){
		viewer.setStatusMessageContent(((StatusMessage)feed).getMessage());
		FacebookFriendDao dao = new FacebookFriendDao();
		FacebookFriend friend = dao.find(((StatusMessage)feed).getFromId_());
		if(null==friend){
			FacebookRestHandler handler = new FacebookRestHandler();
			friend=handler.getFacebookUser(((StatusMessage)feed).getFromId_());
		}
		viewer.setStatusMessageCreator(friend.getName());
		viewer.setStatusMessageDate(((StatusMessage)feed).getCreatedTime_());
		dao=null;
	}
	
	@SuppressWarnings("deprecation")
	public void showFeed(){
		viewer.show(true);
	}
}
