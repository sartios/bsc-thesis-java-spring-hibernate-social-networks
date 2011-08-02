package com.sones.businessLogic.Facebook.Source;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;


public class FacebookFriendList {

	/**
	 * The container which wraps the friends
	 */
	private List<FacebookFriend> friendList_ ;
	
	/**
	 * Index of the list
	 */
	private int index_;
	
	/**
	 * Default index value
	 */
	private final int defaultIndexValue_=-1;
	
	public FacebookFriendList(){
		friendList_ = new ArrayList<FacebookFriend>();
		index_=defaultIndexValue_;
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
	
	/**
	 * @return usernames of the friends
	 */
	public List getUsernames(){
		List<String> usernames = new ArrayList<String>();
		for(int i=0;i<friendList_.size();i++){
			usernames.add(friendList_.get(i).getName());
		}
		/*return usernames.toArray();*/
		return usernames;
	}
	
	public int getSize(){
		return this.friendList_.size();
	}
	
	/**
	 * Makes the {@link #index_} +1
	 */
	private void increaseIndex(){
		index_++;
	}
	
	/**
	 * Clears the {@link #index_}
	 */
	private void clearIndex(){
		index_=defaultIndexValue_;
	}
	
	/**
	 * Get the next friend from the list
	 * @return facebook friend
	 */
	public FacebookFriend getNext(){
		this.increaseIndex();
		if(index_<this.getSize()){
			return this.friendList_.get(index_);
		}
		return null;
	}
	
	/**
	 * If the list contains more friends, it returns true
	 * @return true if there are more friends
	 */
	public boolean hasNext(){
		if(index_<this.getSize()){
			return true;
		}
		return false;
	}
	
	/**
	 * Reset the index to the default value, so we can get the list from the begining
	 */
	public void reset(){
		this.clearIndex();
	}
}
