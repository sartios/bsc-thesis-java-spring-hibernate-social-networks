package com.sones.businessLogic.Facebook;

import org.junit.Test;
import static org.junit.Assert.*;

public class FacebookFriendTest {

	@Test
	public void FacebookFriendsAreEqual_Test(){
		FacebookFriend friend1 = new FacebookFriend("sartios", "12345678");
		FacebookFriend friend2 = new FacebookFriend("sartios", "12345678");
		
		assertEquals(friend1, friend2);
	}
}
