package com.sones.facebook.keywordSearcher.dao.hibernate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sones.dao.IGenericDao;
import com.sones.facebook.dao.feed.IFacebookPostDao;
import com.sones.facebook.dao.hibernate.feed.HibernateFacebookPostDao;
import com.sones.facebook.dao.hibernate.source.HibernateUserDao;
import com.sones.facebook.dao.source.IUserDao;
import com.sones.facebook.keywordSearcher.dao.IFacebookPostKeywordResultDao;
import com.sones.facebook.keywordSearcher.dao.IKeywordDao;
import com.sones.facebook.keywordSearcher.dao.IKeywordSearchDao;
import com.sones.facebook.keywordSearcher.model.FacebookPostKeywordResult;
import com.sones.facebook.keywordSearcher.model.KeywordSearch;
import com.sones.facebook.model.feed.FacebookPost;
import com.sones.usermanager.dao.hibernate.HibernateApplicationUserDao;
import com.sones.usermanager.dao.IApplicationUserDao;
import com.sones.usermanager.model.ApplicationUser;

import static org.junit.Assert.*;

public class HibernateKeywordSearchDaoTester 
{
	private	ApplicationContextProvider	contextProvider;
	private	KeywordSearch	keywordSearch;
	private	IKeywordSearchDao	keywordSearchDao;
	private	IApplicationUserDao	appUserDao;
	private	IFacebookPostKeywordResultDao	resultDao;
	private	IFacebookPostDao	facebookPostDao;
	private	IUserDao	facebookUserDao;
	private	IKeywordDao	keywordDao;
	
	public HibernateKeywordSearchDaoTester()
	{
		contextProvider	=	new	ApplicationContextProvider();
	}
	
	@Before
	public void setUp()
	{
		keywordSearch	=	(KeywordSearch) contextProvider.getModelContext().getBean("keywordSearch");
		keywordSearchDao	=	(HibernateKeywordSearchDao)contextProvider.getDaoContext().getBean("keywordSearchDao");
		appUserDao	=	(HibernateApplicationUserDao)contextProvider.getAppUserContext().getBean("applicationUserDao");
		resultDao	=	(HibernateFacebookPostKeywordResultDao)contextProvider.getDaoContext().getBean("facebookKeywordResultDao");
		facebookPostDao = (HibernateFacebookPostDao)contextProvider.getModelContext().getBean("facebookPostDao");
		facebookUserDao	=	(HibernateUserDao)contextProvider.getModelContext().getBean("facebookUserDao");
		keywordDao	=	(HibernateKeywordDao)contextProvider.getDaoContext().getBean("keywordDao");
	}
	
	@After
	public void tearDown()
	{
		keywordSearch = null;
		keywordSearchDao = null;
	}
	
	@Test
	public void testSaveKeywordSearch()
	{
		appUserDao.Save(keywordSearch.getUser());
		Set<FacebookPostKeywordResult> results = keywordSearch.getResults();
		Iterator<FacebookPostKeywordResult> iterator = results.iterator();
		FacebookPostKeywordResult result = iterator.next();
		FacebookPost facebookPost = result.getPost();
		facebookUserDao.Save(facebookPost.getUser());
		facebookPostDao.Save(facebookPost);
		keywordDao.Save(result.getKeyword());
		keywordSearch.setResults(null);
		keywordSearchDao.Save(keywordSearch);
		resultDao.Save(result);
		KeywordSearch dbKeywordSearch = keywordSearchDao.GetById(keywordSearch.getId());
		assertEquals(keywordSearch.getId(), dbKeywordSearch.getId());
		assertEquals(keywordSearch.getUser(), dbKeywordSearch.getUser());
		assertEquals(results, dbKeywordSearch.getResults());
		resultDao.Delete(result);
		facebookPostDao.Delete(facebookPost);
		facebookUserDao.Delete(facebookPost.getUser());
		keywordSearchDao.Delete(keywordSearch);
		appUserDao.Delete(keywordSearch.getUser());
	}
	
	@Test
	public void getLastKeywordSearchByAppUser()
	{
		KeywordSearch search = keywordSearchDao.getLastKeywordSearchByAppUser( keywordSearch.getUser() );
		assertNotNull(search);
		assertEquals("2000-01-03 00:00:00.0", search.getDate().toString());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getAfterDateByAppUserShouldThrowOnNullDate()
	{
		keywordSearchDao.getAfterDateByAppUser(null, new ApplicationUser());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getAfterDateByAppUserShouldThrowOnNullAppUser()
	{
		keywordSearchDao.getAfterDateByAppUser(Calendar.getInstance().getTime(), null);
	}
	
	@Test
	public void getAfterDateByAppUserShouldWorkWhenAppUserDoesNotExist()
	{
		ApplicationUser nonExistentantAppUser = new ApplicationUser();
		nonExistentantAppUser.setId("aaa");
		keywordSearchDao.getAfterDateByAppUser(Calendar.getInstance().getTime(),nonExistentantAppUser );
	}
	
	@Test
	public void getAfterDateByAppUserShouldWorkCorrectly()
	{
		ApplicationUser appUser = new ApplicationUser();
		appUser.setId("15");
		saveIfDoesNotExist(appUser,appUser.getId(),appUserDao);
		
		ApplicationUser appUser2 = new ApplicationUser();
		appUser2.setId("16");
		saveIfDoesNotExist(appUser2,appUser2.getId(),appUserDao);
		
		List<KeywordSearch> searches = new ArrayList<KeywordSearch>();
		for(int searchIndex = 0 ; searchIndex < 5; searchIndex++)
		{
			KeywordSearch search = new KeywordSearch();
			search.setId( String.valueOf(searchIndex) );
			search.setDate( Calendar.getInstance().getTime() );
			search.setUser(appUser);
			saveIfDoesNotExist(search,search.getId(),keywordSearchDao);
			searches.add(search);
			sleep(1000);
		}
		KeywordSearch search = new KeywordSearch();
		search.setId( String.valueOf(5) );
		search.setDate( Calendar.getInstance().getTime() );
		search.setUser(appUser2);
		saveIfDoesNotExist(search,search.getId(),keywordSearchDao);
		searches.add(search);
		
		Date date = searches.get(0).getDate();
		Collection<KeywordSearch> results = keywordSearchDao.getAfterDateByAppUser(date, appUser2);
		
		assertEquals(1, results.size());
		
		for(KeywordSearch keySearch : searches)
		{
			deleteIfExists(keySearch, keySearch.getId(), keywordSearchDao);
		}
		deleteIfExists(appUser, appUser.getId(), appUserDao);
		deleteIfExists(appUser2, appUser2.getId(), appUserDao);
	}
	
	@Test
	public void getAfterDateByAppUserShouldWorkCorrectlyForPreviousDate()
	{
		Calendar gregCalendar = Calendar.getInstance();
		gregCalendar.add(Calendar.MINUTE, -15);
		Date dateToSearch = gregCalendar.getTime();
		
		ApplicationUser appUser = new ApplicationUser();
		appUser.setId("15");
		saveIfDoesNotExist(appUser,appUser.getId(),appUserDao);
		
		ApplicationUser appUser2 = new ApplicationUser();
		appUser2.setId("16");
		saveIfDoesNotExist(appUser2,appUser2.getId(),appUserDao);
		
		List<KeywordSearch> searches = new ArrayList<KeywordSearch>();
		for(int searchIndex = 0 ; searchIndex < 5; searchIndex++)
		{
			KeywordSearch search = new KeywordSearch();
			search.setId( String.valueOf(searchIndex) );
			search.setDate( Calendar.getInstance().getTime() );
			search.setUser(appUser);
			saveIfDoesNotExist(search,search.getId(),keywordSearchDao);
			searches.add(search);
			sleep(1000);
		}
		KeywordSearch search = new KeywordSearch();
		search.setId( String.valueOf(5) );
		search.setDate( Calendar.getInstance().getTime() );
		search.setUser(appUser2);
		saveIfDoesNotExist(search,search.getId(),keywordSearchDao);
		searches.add(search);
		
		Collection<KeywordSearch> results = keywordSearchDao.getAfterDateByAppUser(dateToSearch, appUser2);
		
		assertEquals(1, results.size());
		
		for(KeywordSearch keySearch : searches)
		{
			deleteIfExists(keySearch, keySearch.getId(), keywordSearchDao);
		}
		deleteIfExists(appUser, appUser.getId(), appUserDao);
		deleteIfExists(appUser2, appUser2.getId(), appUserDao);
	}
	
	private void sleep(long miliseconds)
	{
		try {
			Thread.sleep(miliseconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void saveIfDoesNotExist(Object object, Object id, IGenericDao dao)
	{
		if(dao.GetById(id) == null)
		{
			dao.Save(object);
		}
	}
	
	private void deleteIfExists(Object object, Object id, IGenericDao dao)
	{
		if(dao.GetById(id) != null)
		{
			dao.Delete(object);
		}
	}
}