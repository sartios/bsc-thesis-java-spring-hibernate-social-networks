package com.sones.dao;

import java.util.List;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import com.sones.businessLogic.Facebook.FacebookFriend;
import com.sones.businessLogic.Facebook.FacebookFriendList;

import static org.junit.Assert.*;

public class FacebookFriendDaoTest {
	
	private FacebookFriendDao dao_;
	private FacebookFriend friend_;
	private String name_;
	private String id_;
	
	@Before
	public void setUp(){
		dao_ = new FacebookFriendDao();
		name_="Friend Name";
		id_="Friend ID";
		friend_=new FacebookFriend(name_, id_);
	}
	
	@After
	public void tearDown(){
		dao_=null;
	}
	
	@Test
	public void saveOrUpdate_ConcreteFacebookFriend_Test(){
		dao_.saveOrUpdate(friend_);
		assertNotNull(dao_.find(id_));
	}
	
	@Test
	public void saveOrUpdate_FacebookFriendExists_Test(){
		dao_.saveOrUpdate(friend_);
		dao_.saveOrUpdate(friend_);
		assertNotNull(dao_.find(id_));
	}
	
	@Test
	public void saveOrUpdate_FacebookFriendIsNull_Test(){
		boolean notThrowing=false;
		try{
			dao_.saveOrUpdate(null);
			notThrowing=true;
		}
		catch (Exception e) {
			notThrowing=false;
		}
		assertTrue(notThrowing);
	}
	
	@Test
	public void saveOrUpdate_FriendHasNotName_Test(){
		String friedID="Has not name";
		FacebookFriend friend = new FacebookFriend("", friedID);
		dao_.saveOrUpdate(friend);
		assertNull(dao_.find(friedID));		
	}
	
	@Test
	public void saveOrUpdate_FriendHasNotID_Test(){
		String friedName="Has not id";
		FacebookFriend friend = new FacebookFriend(friedName, "");
		dao_.saveOrUpdate(friend);
		assertNull(dao_.find(friedName));		
	}
	
	@Test
	public void saveOrUpdate_FriendHasNotIDAndHasNotName_Test(){
		String friedName="";
		String friendID="";
		FacebookFriend friend = new FacebookFriend(friedName, friendID);
		dao_.saveOrUpdate(friend);
		assertNull(dao_.find(friedName));		
	}
	
	@Test
	public void delete_FriendExists_Test(){
		FacebookFriend friend=new FacebookFriend(name_, id_);
		dao_.saveOrUpdate(friend_);
		assertTrue(dao_.delete(friend));
	}
	
	@Test
	public void delete_FriendDoesNotExist_Test(){
		String name="Doesn't exist";
		String id="Doesn't exist";
		FacebookFriend friend=new FacebookFriend(name, id);
		dao_.delete(friend);
		assertTrue(dao_.delete(friend));
	}
	
	@Test
	public void delete_FriendWithNoName_Test(){
		String name="";
		String id="Friend with no name";
		FacebookFriend friend=new FacebookFriend(name, id);;
		assertFalse(dao_.delete(friend));
	}
	
	@Test
	public void delete_FriendWithNoID_Test(){
		String name="Friend with no id";
		String id="";
		FacebookFriend friend=new FacebookFriend(name, id);
		assertFalse(dao_.delete(friend));
	}
	
	@Test
	public void delete_FriendIsNull_Test(){
		assertFalse(dao_.delete(null));
	}
	
	@Test
	public void find_FriendExists_Test(){
		dao_.saveOrUpdate(friend_);
		assertNotNull(dao_.find(id_));
	}
	
	@Test
	public void find_FriendDoesNotExist_Test(){
		dao_.delete(friend_);
		assertNull(dao_.find(id_));
	}
	
	@Test
	public void find_IdIsNull_Test(){
		assertNull(dao_.find(null));
	}
	
	@Test
	public void findFriendByName_NameExists_Test(){
		dao_.saveOrUpdate(friend_);
		assertNotNull(dao_.findByName(name_));
	}
	
	@Test
	public void findFriendByName_FriendDoesNotExist_Test(){
		dao_.delete(friend_);
		assertNull(dao_.findByName(name_));
	}
	
	@Test
	public void findFriendByName_IdIsNull_Test(){
		assertNull(dao_.findByName(null));
	}
	
	@Test
	public void findAll_ThereAreFriends_Test(){
		FacebookFriendList friends = dao_.findAll();
		assertFalse(friends.getFriendList().isEmpty());
	}
	
	@Test
	public void findAll_ThereAreNotFriends_Test(){
		FacebookFriendList friends = dao_.findAll();
		deleteAllFriends(friends);
		FacebookFriendList friendsAfterDeleteThem=dao_.findAll();
		saveAllFriends(friends);
		assertTrue(friendsAfterDeleteThem.getFriendList().isEmpty());
		
		
	}
	
	private void deleteAllFriends(final FacebookFriendList friends){
		List<FacebookFriend> listOfFriends = friends.getFriendList();
		int friendsSize = listOfFriends.size();
		for(int i=0;i<friendsSize;i++){
			dao_.delete(listOfFriends.get(i));
		}
	}
	
	private void saveAllFriends(final FacebookFriendList friends){
		List<FacebookFriend> listOfFriends = friends.getFriendList();
		int friendsSize = listOfFriends.size();
		for(int i=0;i<friendsSize;i++){
			dao_.saveOrUpdate(listOfFriends.get(i));
		}
	}
}
