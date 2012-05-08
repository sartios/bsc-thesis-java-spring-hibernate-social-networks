package com.sones.facebook.keywordSearcher.logic;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.dao.IGenericDao;
import com.sones.facebook.keywordSearcher.dao.IFacebookPostKeywordResultDao;
import com.sones.facebook.keywordSearcher.dao.IKeywordDao;
import com.sones.facebook.keywordSearcher.dao.IKeywordSearchDao;
import com.sones.facebook.keywordSearcher.dao.hibernate.HibernateFacebookPostKeywordResultDao;
import com.sones.facebook.keywordSearcher.dao.hibernate.HibernateKeywordDao;
import com.sones.facebook.keywordSearcher.dao.hibernate.HibernateKeywordSearchDao;
import com.sones.facebook.keywordSearcher.model.FacebookPostKeywordResult;
import com.sones.facebook.keywordSearcher.model.Keyword;
import com.sones.facebook.keywordSearcher.model.KeywordSearch;
import com.sones.usermanager.model.ApplicationUser;
import com.sones.usermanager.dao.IApplicationUserDao;
import com.sones.usermanager.dao.hibernate.HibernateApplicationUserDao;

public class KeywordRetrieverServiceTester 
{
	private KeywordRetrieverService service;
	private IApplicationUserDao appUserDao;
	private IKeywordSearchDao keywordSearchDao;
	private IFacebookPostKeywordResultDao resultDao;
	private IKeywordDao keywordDao;
	
	public KeywordRetrieverServiceTester()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("KeywordRetriever/spring-keywordretriever-service-nu.xml");
		appUserDao = (HibernateApplicationUserDao)context.getBean("appUserDao");
		keywordSearchDao = (HibernateKeywordSearchDao)context.getBean("keywordSearchDao");
		resultDao = (HibernateFacebookPostKeywordResultDao)context.getBean("resultDao");
		keywordDao = (HibernateKeywordDao)context.getBean("keywordDao");
	}
	
	@Test
	public void testUpdateKeywordInfoWhenSomeKeywordsExist()
	{
		service = new KeywordRetrieverService();
		List<FacebookPostKeywordResult> results = new ArrayList<FacebookPostKeywordResult>();
		for(int resultIndex = 0 ; resultIndex < 5 ; resultIndex++)
		{
			FacebookPostKeywordResult result = new FacebookPostKeywordResult();
			result.setId( String.valueOf(resultIndex) );
			Keyword keyword = new Keyword();
			keyword.setId( String.valueOf( resultIndex ) );
			result.setKeyword(keyword);
			results.add(result);
		}
		Keyword keyword = results.iterator().next().getKeyword();
		FacebookPostKeywordResult result1 = new FacebookPostKeywordResult();
		result1.setKeyword(keyword);
		results.add(result1);
		FacebookPostKeywordResult result2 = new FacebookPostKeywordResult();
		result2.setKeyword(keyword);
		results.add(result2);
		assertEquals(7, results.size());
		service.updateKeywordInfo(results);
		Map<Keyword,Long> keywordInfo = service.getKeywordInfo();
		Long value = keywordInfo.get(keyword);
		assertEquals(5, keywordInfo.size());
		assertEquals(new Long(3),value);
	}
	
	@Test
	public void testUpdateKeywordInfoIntervalBehavior()
	{
		service = new KeywordRetrieverService();
		List<FacebookPostKeywordResult> results = new ArrayList<FacebookPostKeywordResult>();
		for(int resultIndex = 0 ; resultIndex < 5 ; resultIndex++)
		{
			FacebookPostKeywordResult result = new FacebookPostKeywordResult();
			result.setId( String.valueOf(resultIndex) );
			Keyword keyword = new Keyword();
			keyword.setId( String.valueOf( resultIndex ) );
			result.setKeyword(keyword);
			results.add(result);
		}
		Keyword keyword = results.iterator().next().getKeyword();
		FacebookPostKeywordResult result1 = new FacebookPostKeywordResult();
		result1.setKeyword(keyword);
		results.add(result1);
		FacebookPostKeywordResult result2 = new FacebookPostKeywordResult();
		result2.setKeyword(keyword);
		results.add(result2);
		assertEquals(7, results.size());
		service.updateKeywordInfo(results);
		service.updateKeywordInfo(results);
		service.updateKeywordInfo(results);
		Map<Keyword,Long> keywordInfo = service.getKeywordInfo();
		Long value = keywordInfo.get(keyword);
		assertEquals(5, keywordInfo.size());
		assertEquals(new Long(9),value);
	}
	
	@Test
	public void testFindDateBeforeLessThanHour()
	{
		service = new KeywordRetrieverService();
		Date date = service.findDateBefore(-50);
		Date now = Calendar.getInstance().getTime();
		assertEquals(true, date.before(now));
	}
	
	@Test
	public void testFindDateBeforeMoreThanHour()
	{
		service = new KeywordRetrieverService();
		Date date = service.findDateBefore(-120);
		Date now = Calendar.getInstance().getTime();
		assertEquals(true, date.before(now));
	}
	
	@Test
	public void testGetKeywordsThatWorksCorrectly()
	{
		ApplicationUser user = new ApplicationUser();
		user.setId("10059");
		saveIfDoesNotExist(user, user.getId(),appUserDao);
		
		List<KeywordSearch> searches = new ArrayList<KeywordSearch>();
		for(int searchIndex = 0; searchIndex < 5; searchIndex++)
		{
			KeywordSearch search = new KeywordSearch();
			search.setId( String.valueOf( searchIndex ) );
			search.setDate( Calendar.getInstance().getTime() );
			search.setUser(user);
			saveIfDoesNotExist(search, search.getId(), keywordSearchDao);
			searches.add(search);
			sleep(1000);
		}
		List<FacebookPostKeywordResult> results = new ArrayList<FacebookPostKeywordResult>();
		List<Keyword> keywords = new ArrayList<Keyword>();
		SecureRandom random = new SecureRandom();
		for(KeywordSearch search : searches)
		{
			FacebookPostKeywordResult result = new FacebookPostKeywordResult();
			result.setId(String.valueOf( new BigInteger(8,random )));
			result.setSearch(search);
			result.setUser(user);
			Keyword keyword = new Keyword(String.valueOf( new BigInteger(8,random )));
			keyword.setId(String.valueOf( new BigInteger(8,random )));
			saveIfDoesNotExist(keyword, keyword.getId(), keywordDao);
			keywords.add(keyword);
			result.setKeyword(keyword);
			results.add(result);
			saveIfDoesNotExist(result, result.getId(), resultDao);
		}
		
		Keyword keyword1 = results.get(0).getKeyword();
		Keyword keyword2 = results.get(1).getKeyword();
		KeywordSearch search1 = searches.get(0);
		KeywordSearch search2 = searches.get(1);
		for(int resultIndex = 0; resultIndex < 15; resultIndex++)
		{
			FacebookPostKeywordResult result1 = new FacebookPostKeywordResult();
			result1.setId(String.valueOf( new BigInteger(16,random )));
			result1.setKeyword(keyword1);
			result1.setUser(user);
			result1.setSearch(search1);
			saveIfDoesNotExist(result1, result1.getId(), resultDao);
			results.add(result1);
			FacebookPostKeywordResult result2 = new FacebookPostKeywordResult();
			result2.setId(String.valueOf( new BigInteger(15,random )));
			result2.setKeyword(keyword2);
			result2.setSearch(search2);
			result2.setUser(user);
			saveIfDoesNotExist(result2, result2.getId(), resultDao);
			results.add(result2);
		}
		
		service = new KeywordRetrieverService(keywordSearchDao, resultDao);
		Map<Keyword,Long> serviceResults = service.getKeywords(user,9, -50);
		
		assertEquals(2, serviceResults.size());
		assertEquals(16, serviceResults.get(keyword1).intValue());
		
		for(FacebookPostKeywordResult result : results)
		{
			deleteIfExists(result, result.getId(), resultDao);
		}
		
		for(KeywordSearch keySearch : searches)
		{
			deleteIfExists(keySearch, keySearch.getId(), keywordSearchDao);
		}
		
		for(Keyword keyword : keywords)
		{
			deleteIfExists(keyword, keyword.getId(), keywordDao);
		}
		
		deleteIfExists(user, user.getId(), appUserDao);
	}
	
	private void sleep(int miliseconds)
	{
		try {
			Thread.sleep(miliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void saveIfDoesNotExist(Object model,Object id,IGenericDao dao)
	{
		if(dao.GetById(id) == null)
		{
			dao.Save(model);
		}
	}
	
	private void deleteIfExists(Object model,Object id,IGenericDao dao)
	{
		if(dao.GetById(id) != null)
		{
			dao.Delete(model);
		}
	}
}
