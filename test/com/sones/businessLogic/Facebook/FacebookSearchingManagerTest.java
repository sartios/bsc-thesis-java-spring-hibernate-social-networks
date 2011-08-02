package com.sones.businessLogic.Facebook;

import static org.junit.Assert.*;

import java.util.Timer;

import org.junit.Test;

import com.sones.businessLogic.SocialNetworkUser;
import com.sones.businessLogic.Facebook.Source.FacebookOfflineUser;

public class FacebookSearchingManagerTest{

	@Test
	public void run_createTimerForTwoSearches_Test(){
		Timer timer = new Timer();
		SocialNetworkUser user = new FacebookOfflineUser();
		FacebookSearchingManager fsm = new FacebookSearchingManager(user);
		fsm.run();	
		fsm.run();		

	}
}
