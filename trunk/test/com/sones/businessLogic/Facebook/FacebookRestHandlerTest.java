package com.sones.businessLogic.Facebook.v2;

import org.junit.Test;
import static org.junit.Assert.*;

import com.sones.businessLogic.Facebook.FacebookFriendList;
import com.sones.businessLogic.Facebook.FacebookRestHandler;

public class FacebookRestHandlerTest {

	@Test
	public void getFriendsList_Test(){
		FacebookRestHandler rest = new FacebookRestHandler();
		FacebookFriendList friends = rest.getFriendList();
		assertNotSame(0, friends.getSize());
	}
}
