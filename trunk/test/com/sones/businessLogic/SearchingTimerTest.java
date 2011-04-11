package com.sones.businessLogic;

import org.junit.Test;

import com.sones.businessLogic.Facebook.FacebookSearchingList;
import com.sones.businessLogic.Facebook.FacebookSearchingManager;

public class SearchingTimerTest {

	@Test
	public void start_Test(){
		FacebookSearchingManager manager = new FacebookSearchingManager();
		FacebookSearchingList sources = new FacebookSearchingList();
		sources.addID("502894293");
		sources.addID("100000866964787");
		manager.setSources(sources);
		manager.setSearcher(new KeywordSearcher());
		
		SearchingTimer timer = new SearchingTimer(1000);
		timer.setFacebookSearchingManager(manager);
		timer.start();
	}
}
