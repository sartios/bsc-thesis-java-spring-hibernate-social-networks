package com.sones.businessLogic.Facebook;

import org.junit.Test;
import static org.junit.Assert.*;

public class FacebookDataManagerTest {
	
	/**
	 * Tests if getFeedsFrom can take feeds for more than one facebook user
	 */
	@Test
	public void getFeedsFrom_moreThanOneUser_Test(){
		String ID = "100000866964787";
		final String TOKEN = "access_token=106911326013695|ea1c5d947c3788fa382b0abf-747618741|MeTjo25aPlPeqlEucqb4ZoZe50Y";
		FacebookRestHandler handler = new FacebookRestHandler();
		
		FacebookFriendList list=new FacebookFriendList();
		list=handler.getFriendList(ID, TOKEN);
		FacebookSearchingList sources = new FacebookSearchingList();
		sources.addID(list.getFriendList().get(0).getId());
		sources.addID(list.getFriendList().get(1).getId());
		
		FacebookDataManager manager = new FacebookDataManager();
		assertFalse(manager.getFeedsFrom(sources, TOKEN).isEmpty());
	}
	
	/**
	 * Tests if getFeedsFrom can take feeds from facebook users and groups
	 */
	@Test
	public void getFeedsFrom_UsersAndGroups_Test(){
		String ID = "100000866964787";
		final String TOKEN = "access_token=106911326013695|ea1c5d947c3788fa382b0abf-747618741|MeTjo25aPlPeqlEucqb4ZoZe50Y";
		FacebookRestHandler handler = new FacebookRestHandler();
		
		FacebookFriendList friends=new FacebookFriendList();
		friends=handler.getFriendList(ID, TOKEN);
		FacebookSearchingList sources = new FacebookSearchingList();
		sources.addID(friends.getFriendList().get(0).getId());
		sources.addID(friends.getFriendList().get(1).getId());
		
		FacebookGroupList groups = new FacebookGroupList();
		groups = handler.getGroups(ID, TOKEN);
		sources.addID(groups.getGroups().get(2).getID());
		sources.addID(groups.getGroups().get(3).getID());
		
		FacebookDataManager manager = new FacebookDataManager();
		assertFalse(manager.getFeedsFrom(sources, TOKEN).isEmpty());
	}
}
