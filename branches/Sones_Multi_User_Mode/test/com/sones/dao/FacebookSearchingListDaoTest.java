package com.sones.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.sones.businessLogic.Facebook.Source.FacebookFriend;
import com.sones.businessLogic.Facebook.Source.FacebookGroup;
import com.sones.businessLogic.Facebook.FacebookSearchingList;

public class FacebookSearchingListDaoTest {

	@Test
	public void findNames_OnlyFriend_Test(){
		FacebookFriend friend = new FacebookFriend("Tested","007");
		FacebookFriendDao dao = new FacebookFriendDao();
		dao.saveOrUpdate(friend);
		dao=null;
		FacebookSearchingList sources = new FacebookSearchingList();
		sources.addID(friend.getId());
		FacebookSearchingListDao sourcesDao = new FacebookSearchingListDao();
		List<String> names = sourcesDao.findNames(sources);
		
		assertTrue(friend.getName().equals(names.get(0).toString()));
		
	}
	
	@Test
	public void findNames_OnlyGroup_Test(){
		FacebookGroup group = new FacebookGroup();
		group.setId("007");
		group.setName("Tested");
		FacebookGroupDao dao = new FacebookGroupDao();
		dao.saveOrUpdate(group);
		dao=null;
		FacebookSearchingList sources = new FacebookSearchingList();
		sources.addID(group.getId());
		FacebookSearchingListDao sourcesDao = new FacebookSearchingListDao();
		List<String> names = sourcesDao.findNames(sources);
		
		assertTrue(group.getName().equals(names.get(0).toString()));
		
	}
}
