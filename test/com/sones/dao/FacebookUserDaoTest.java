package com.sones.dao;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sones.businessLogic.Facebook.Source.FacebookUser;

import static org.junit.Assert.*;


public class FacebookUserDaoTest {

	private FacebookUser facebookUser;
	private String userID;
	private String facebookID;
	
	@Before
	public void setUp(){
		userID=new String("0001");
		facebookID=new String("5555");
		facebookUser = new FacebookUser(facebookID,userID);	
	}
	
	@After
	public void tearDown(){
	}
	
	@Test
	public void saveOrUpdate_FacebookUserWhichIsValidApplicationUser_Test(){
		FacebookUserDao dao = new FacebookUserDao();
		boolean result=dao.saveOrUpdate(facebookUser);
		dao.delete(facebookUser);
		assertTrue(result);
	}
	
	@Test
	public void saveOrUpdate_FacebookUserIsNotValidApplicationUser_Test(){
		FacebookUserDao dao = new FacebookUserDao();
		facebookUser.setApplicationUserID_("12345");
		boolean result=dao.saveOrUpdate(facebookUser);
		dao.delete(facebookUser);
		assertFalse(result);
	}
	
	@Test
	public void saveOrUpdate_FacebookUserIsNull_Test(){
		FacebookUserDao dao = new FacebookUserDao();
		assertFalse(dao.saveOrUpdate(null));
	}
	
	@Test
	public void delete_UserExists_Test(){
		FacebookUserDao dao = new FacebookUserDao();
		dao.saveOrUpdate(facebookUser);
		boolean result=dao.delete(facebookUser);
		assertTrue(result);
	}
	
	@Test
	public void delete_UserDoesNotExist_Test(){
		FacebookUserDao dao = new FacebookUserDao();
		FacebookUser user = new FacebookUser("DoesNotExit", "DoesNotExist");
		boolean result=dao.delete(facebookUser);
		assertFalse(result);
	}
	
	
	@Test
	public void delete_UserIsNull_Test(){
		FacebookUserDao dao = new FacebookUserDao();
		assertFalse(dao.delete(null));
	}
	
	@Test
	public void find_UserExists_Test(){
		FacebookUserDao dao = new FacebookUserDao();
		dao.saveOrUpdate(facebookUser);
		FacebookUser user = dao.find(facebookID);
		dao.delete(facebookUser);
		assertNotNull(user);
	}
	
	@Test
	public void find_UserDoesNotExist_Test(){
		FacebookUserDao dao = new FacebookUserDao();
		FacebookUser user = dao.find(facebookID);
		assertNull(user);
	}
}
