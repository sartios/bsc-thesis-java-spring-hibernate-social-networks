package com.sones.businessLogic.Facebook.v2;

import java.util.Set;

import org.junit.Test;
import static org.junit.Assert.*;

import com.sones.businessLogic.Keyword;
import com.sones.businessLogic.Facebook.FacebookSearchingList;
import com.sones.businessLogic.Facebook.FacebookSearchingManager_old;
import com.sones.dao.KeywordDao;

public class FacebookSearchingManagerTest {

	@Test
	public void getPopularFeeds_Test(){
		FacebookSearchingManager_old manager = new FacebookSearchingManager_old();
		FacebookSearchingList sources = new FacebookSearchingList();
		sources.addID("100000866964787");
		Set<String> feeds = manager.getPopularFeeds(sources, 1);
		System.out.println(feeds.size());

		
		boolean itWorks = false;
		if(0!=feeds.size()){
			itWorks = true;
		}
		System.out.println(itWorks);
		assertTrue(itWorks);
	}
	
	@Test
	public void findKeywordsInFeeds_Test(){
		FacebookSearchingManager_old manager = new FacebookSearchingManager_old();
		FacebookSearchingList sources = new FacebookSearchingList();
		sources.addID("100000866964787");
		Keyword keyword = new Keyword("test");
		KeywordDao dao = new KeywordDao();
		dao.saveOrUpdate(keyword);
		manager.findKeywordsInFeeds(sources);
	}
}
