package com.sones.facebook.downloader.logic;

import com.sones.facebook.downloader.model.FacebookFriend;

public interface IFacebookFriendDownloaderService 
{
	public void downloadFacebookFriends(String facebookUserId);
	
	public Iterable<FacebookFriend> getFacebookFriends(String facebookUserId);
}
