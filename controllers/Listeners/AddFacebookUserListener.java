package com.sones.controllers.Listeners;

import java.awt.event.MouseAdapter;
import java.util.List;

import javax.swing.JList;

import com.sones.businessLogic.Facebook.FacebookFriend;
import com.sones.businessLogic.Facebook.FacebookFriendList;
import com.sones.businessLogic.Facebook.FacebookSearchingList;

public class AddFacebookUserListener extends MouseAdapter{
	
	private int[] friendsIndexes_ = null;
	private FacebookFriendList facebookFriends_ = null;
	private FacebookSearchingList sources_=null;
	
	public AddFacebookUserListener(JList friendsUsernames,FacebookFriendList facebookFriends,FacebookSearchingList sources){
		//friendsUsernames_= friendsUsernames;
		facebookFriends_ = facebookFriends;
		sources_= sources;
	}
	
	public void mouseClicked(java.awt.event.MouseEvent e) {
		//int[] indexes = friendsUsernames_.getSelectedIndices();
		List friends = facebookFriends_.getFriendList();
		for(int i=0;i<friendsIndexes_.length;i++){
			String currentID = ((FacebookFriend)friends.get(friendsIndexes_[i])).getId();
			if(sources_.getIDs().contains(currentID)){
				break;
			}
			sources_.addID(currentID);
		}
	}
}
