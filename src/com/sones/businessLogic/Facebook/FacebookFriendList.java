package com.sones.businessLogic.Facebook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class FacebookFriendList {

	/**
	 * The container which wraps the friends
	 */
	private List<FacebookFriend> friendList_ ;
	
	public FacebookFriendList(){
		friendList_ = new ArrayList<FacebookFriend>();
	}
	/**
	 * We add a friend to the list
	 */
	public void addFriend(final FacebookFriend friend){
		if(null!=friend){
			friendList_.add(friend);
		}
	}
	
	/**
	 * @return friend list
	 */
	public List<FacebookFriend> getFriendList(){
		return Collections.unmodifiableList(friendList_);
	}
	
	/**
	 * Remove a friend from the list
	 */
	public boolean removeFriend(FacebookFriend friend){
		return this.friendList_.remove(friend);
	}
}
