package com.sones.businessLogic.Facebook.Source;

import java.util.List;

import org.junit.Test;

import com.sones.businessLogic.Facebook.Source.FacebookFriend;
import com.sones.businessLogic.Facebook.Source.FacebookFriendList;

import static org.junit.Assert.*;

public class FacebookFriendListTest {
	
	@Test
	public void getFriendList_ExceptionOnListAdd_Test(){
		FacebookFriendList list=new FacebookFriendList();
		list.addFriend(new FacebookFriend("Sartios", "12345"));
		list.addFriend(new FacebookFriend("Stelios", "123456"));
		try{
			list.getFriendList().add(new FacebookFriend("Stelios2", "1234526"));
		}
		catch(UnsupportedOperationException e){
			assertTrue("Add to the list from list's native method is unsupported",true);
		}
	}
	
	@Test
	public void getFriendList_ExceptionOnListRemove_Test(){
		FacebookFriendList list=new FacebookFriendList();
		FacebookFriend friend = new FacebookFriend("Sartios", "12345");
		list.addFriend(friend);
		list.addFriend(new FacebookFriend("Stelios", "123456"));
		try{
			list.getFriendList().remove(friend);
		}
		catch(UnsupportedOperationException e){
			assertTrue("Remove from the list from list's native method is unsupported",true);
		}
	}
	
	@Test
	public void removeFriend_FriendExists_Test(){
		FacebookFriendList list=new FacebookFriendList();
		FacebookFriend friend = new FacebookFriend("Sartios", "12345");
		list.addFriend(friend);
		assertTrue("Remove friend from the list should work",list.removeFriend(friend));
	}
}
