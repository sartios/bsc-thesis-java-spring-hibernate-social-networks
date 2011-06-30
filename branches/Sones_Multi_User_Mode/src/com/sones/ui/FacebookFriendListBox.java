/*package com.sones.ui;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import com.sones.businessLogic.Facebook.FacebookFriend;
import com.sones.businessLogic.Facebook.FacebookFriendList;
import com.sones.businessLogic.Facebook.FacebookRestHandler;
import com.sones.businessLogic.Facebook.FacebookSearchingList;
import com.sones.businessLogic.Facebook.FacebookToken;
import com.sones.dao.TokenDao;

public class FacebookFriendListBox extends JList{
	
	private DefaultListModel friendsUsernames = null;
	private FacebookFriendList facebookFriends = null;
	//FacebookSearchingList sources;
	
	public FacebookFriendListBox(){
		friendsUsernames = new DefaultListModel();
		//sources = new FacebookSearchingList();
		TokenDao tokenDao = new TokenDao();
		FacebookToken token = (FacebookToken)tokenDao.findAll().get(0);
		FacebookRestHandler handler=new FacebookRestHandler();
		facebookFriends = handler.getFriendList("100000866964787",token.getToken());
		List list = facebookFriends.getUsernames();
		
		for(int i=0;i<list.size();i++){
			friendsUsernames.add(i, list.get(i));
		}
		this.setModel(friendsUsernames);
	}
	
	*//**
	 * Add to a FacebookSearchingList the sources from which we will get data
	 * @param sources
	 *//*
	public void addSelectedSources(FacebookSearchingList sources){
		
		List friends = facebookFriends.getFriendList();
		int[] indexes = this.getSelectedIndices();
		sources.addID(((FacebookFriend)friends.get(0)).getId());
		
		String id;
		for(int i=0;i<indexes.length;i++){
			String currentID = ((FacebookFriend)friends.get(indexes[i])).getId();
			if(sources.getIDs().contains(currentID)){
				break;
			}
			while(null!=(id=sources.getID())){
				if(id==currentID){
					sources.getIDs().
					break;
				}
			}
			sources.addID(((FacebookFriend)friends.get(i)).getId()currentID);	
		}
		//return sources;
	}
	
	*//**
	 * Deletes the selected sources from the FacebookSearchingList
	 * @param sources
	 *//*
	public void deleteSelectedSources(FacebookSearchingList sources){
		List friends = facebookFriends.getFriendList();
		int[] indexes = this.getSelectedIndices();
		for(int i=0;i<indexes.length;i++){
			String currentID = ((FacebookFriend)friends.get(indexes[i])).getId();
			sources.deleteID(currentID);
		}
	}

}
*/