package com.sones.keyword.selector.logic;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.dao.IGenericDao;
import com.sones.dao.exception.DataAccessLayerException;
import com.sones.facebook.keywordSearcher.dao.IApplicationUserKeywordDao;
import com.sones.facebook.keywordSearcher.dao.IKeywordDao;
import com.sones.facebook.keywordSearcher.model.ApplicationUserKeyword;
import com.sones.facebook.keywordSearcher.model.ApplicationUserKeywordId;
import com.sones.facebook.keywordSearcher.model.Keyword;
import com.sones.usermanager.model.ApplicationUser;
import com.sones.usermanager.dao.IApplicationUserDao;

public class KeywordSelectorServiceTester 
{
	private IKeywordSelectorService service;
	private IKeywordDao keywordDao;
	private IApplicationUserKeywordDao appUserKeywordDao;
	private IApplicationUserDao appUserDao;
	
	public KeywordSelectorServiceTester()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("KeywordSelector/spring-keywordselector-logic-nu.xml");
		service = (IKeywordSelectorService) context.getBean("keywordSelectorService");
		keywordDao = (IKeywordDao) context.getBean( "keywordDao" );
		appUserDao = (IApplicationUserDao) context.getBean( "appUserDao" );
		appUserKeywordDao = (IApplicationUserKeywordDao) context.getBean( "appUserKeywordDao" );
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void selectKeywordShouldThrowExceptionOnNullUser()
	{
		service.selectKeyword(null, new Keyword());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void selectKeywordShouldThrowExceptionOnNullKeyword()
	{
		service.selectKeyword(new ApplicationUser(), null);
	}
	
	@Test(expected = DataAccessLayerException.class)
	public void selectKeywordShouldThrowExceptionWhenKeywordDoesNotExist()
	{
		Keyword keyword = new Keyword("not exist");
		ApplicationUser appUser = new ApplicationUser();
		service.selectKeyword(appUser, keyword);
	}
	
	@Test
	public void selectKeywordShouldWorkCorrectly()
	{
		Keyword keyword = new Keyword("exist");
		Number number = keywordDao.GetRowCount();
		keyword.setId( number.toString() );
		saveIfNotExist(keyword,keyword.getId(),keywordDao);

		ApplicationUser appUser = new ApplicationUser();
		appUser.setId("1111191");
		saveIfNotExist(appUser,appUser.getId(),appUserDao);
		
		service.selectKeyword(appUser, keyword);
		
		ApplicationUserKeyword appUserKeyword = new ApplicationUserKeyword(keyword, appUser);
		ApplicationUserKeyword result = appUserKeywordDao.GetById(appUserKeyword.getId());
		
		assertNotNull( result );
		assertEquals(appUserKeyword.getId(), result.getId());
		
		deleteIfExists(appUserKeyword,appUserKeyword.getId(),appUserKeywordDao);
		deleteIfExists(keyword,keyword.getId(),keywordDao);
		deleteIfExists(appUser,appUser.getId(),appUserDao);
	}
	
	private void saveIfNotExist(Object model, Object id, IGenericDao dao)
	{
		if(dao.GetById(id) == null)
		{
			dao.Save(model);
		}
	}
	
	private void deleteIfExists(Object model, Object id, IGenericDao dao)
	{
		if(dao.GetById(id) != null)
		{
			dao.Delete(model);
		}
	}
}
