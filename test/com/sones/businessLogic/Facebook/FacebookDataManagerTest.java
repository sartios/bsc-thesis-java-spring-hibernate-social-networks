package com.sones.businessLogic.Facebook;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FacebookDataManagerTest {
	
	private String ID;
	private String TOKEN;
	private FacebookRestHandler rest;
	private FacebookFriendList friends;
	private FacebookSearchingList sources ;
	private FacebookDataManager manager;


	
	@Before
	public void setUp(){
		ID = new String("100000866964787");
		TOKEN = new String("access_token=106911326013695|ea1c5d947c3788fa382b0abf-747618741|MeTjo25aPlPeqlEucqb4ZoZe50Y");
		rest = new FacebookRestHandler();
		friends=new FacebookFriendList();
		sources = new FacebookSearchingList();
		manager = new FacebookDataManager();
	}
	
	@After
	public void tearDown(){
		ID = null;
		TOKEN = null;
		rest = null;
		friends= null;
		sources = null;
		manager = null;
	}
	/**
	 * Tests if getFeedsFrom can take feeds for more than one facebook user
	 */
	@Test
	public void getFeedsFrom_moreThanOneUser_Test(){
		friends=rest.getFriendList(ID, TOKEN);
		sources.addID(friends.getFriendList().get(0).getId());
		sources.addID(friends.getFriendList().get(1).getId());
		assertFalse(manager.getFeedsFrom(sources, TOKEN).isEmpty());
	}
	
	/**
	 * Tests if getFeedsFrom can take feeds from facebook users and groups
	 */
	@Test
	public void getFeedsFrom_UsersAndGroups_Test(){
		friends=rest.getFriendList(ID, TOKEN);
		sources.addID(friends.getFriendList().get(0).getId());
		sources.addID(friends.getFriendList().get(1).getId());
		FacebookGroupList groups = new FacebookGroupList();
		groups = rest.getGroups(ID, TOKEN);
		sources.addID(groups.getGroups().get(2).getID());
		sources.addID(groups.getGroups().get(3).getID());
		assertFalse(manager.getFeedsFrom(sources, TOKEN).isEmpty());
	}
}
