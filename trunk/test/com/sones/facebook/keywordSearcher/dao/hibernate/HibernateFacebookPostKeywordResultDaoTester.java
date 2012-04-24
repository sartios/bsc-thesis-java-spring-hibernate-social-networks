package com.sones.facebook.keywordSearcher.dao.hibernate;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.sones.dao.IGenericDao;
import com.sones.facebook.dao.feed.IFacebookPostDao;
import com.sones.facebook.dao.hibernate.feed.HibernateFacebookPostDao;
import com.sones.facebook.dao.hibernate.source.HibernateUserDao;
import com.sones.facebook.dao.source.IUserDao;
import com.sones.facebook.keywordSearcher.dao.IFacebookPostKeywordResultDao;
import com.sones.facebook.keywordSearcher.dao.IKeywordDao;
import com.sones.facebook.keywordSearcher.dao.IKeywordSearchDao;
import com.sones.facebook.keywordSearcher.model.FacebookPostKeywordResult;
import com.sones.facebook.keywordSearcher.model.Keyword;
import com.sones.facebook.keywordSearcher.model.KeywordSearch;
import com.sones.facebook.model.feed.FacebookPost;
import com.sones.facebook.model.source.User;
import com.sones.usermanager.dao.hibernate.HibernateApplicationUserDao;
import com.sones.usermanager.dao.IApplicationUserDao;
import com.sones.usermanager.model.ApplicationUser;

/**
 * 
 * @author sartios.sones@gmail.com.
 *
 */
public class HibernateFacebookPostKeywordResultDaoTester 
{
	/**
	 * Provides the spring contexts for this test.
	 */
	private	ApplicationContextProvider contextProvider;
	
	/**
	 * The user for whom the search run.
	 */
	private	ApplicationUser appUser;
	
	/**
	 * The application user dao.
	 */
	private	IApplicationUserDao appUserDao;
	
	/**
	 * The searching result. This is our main object for this test.
	 */
	private	FacebookPostKeywordResult result;
	
	/**
	 * The result dao.
	 */
	private	IFacebookPostKeywordResultDao resultDao;
	
	/**
	 * The user who posted the facebook post.
	 */
	private User facebookUser;
	
	/**
	 * The facebook user dao.
	 */
	private	IUserDao facebookUserDao;
	
	/**
	 * The facebook post in which the keyword found.
	 */
	private	FacebookPost facebookPost;
	
	/**
	 * The facebook post dao.
	 */
	private	IFacebookPostDao facebookPostDao;
	
	/**
	 * The keyword that was searched into the facebook posts.
	 */
	private Keyword keyword;
	
	/**
	 * The keyword dao.
	 */
	private	IKeywordDao keywordDao;
	
	/**
	 * The keyword search into which the result found.
	 */
	private	KeywordSearch keywordSearch;
	
	/**
	 * The keyword search dao.
	 */
	private	IKeywordSearchDao keywordSearchDao;
	
	/**
	 * Initializes the context provider.
	 */
	public HibernateFacebookPostKeywordResultDaoTester()
	{
		contextProvider = new ApplicationContextProvider();
	}
	
	@Before
	public void setUp()
	{
		result = (FacebookPostKeywordResult) contextProvider.getModelContext().getBean("result");
		resultDao = (HibernateFacebookPostKeywordResultDao)contextProvider.getDaoContext().getBean("facebookKeywordResultDao");
		
		keywordSearch = (KeywordSearch) contextProvider.getModelContext().getBean("keywordSearch");
		keywordSearchDao = (HibernateKeywordSearchDao)contextProvider.getDaoContext().getBean("keywordSearchDao");
		
		keyword = (Keyword)contextProvider.getModelContext().getBean("keyword");
		keywordDao = (HibernateKeywordDao)contextProvider.getDaoContext().getBean("keywordDao");
	
		appUserDao = (HibernateApplicationUserDao)contextProvider.getAppUserContext().getBean("applicationUserDao");
		facebookUserDao = (HibernateUserDao) contextProvider.getModelContext().getBean("facebookUserDao");
		facebookPostDao = (HibernateFacebookPostDao)contextProvider.getModelContext().getBean("facebookPostDao");
		
	}
	
	@After
	public void tearDown()
	{
		
	}
	
	@Test
	public void testSaveFacebookKeywordResult()
	{	
		appUser = result.getSearch().getUser();
		appUserDao.Save(appUser);
		
		keywordSearch = result.getSearch();
		keywordSearch.setResults(null);
		keywordSearchDao.Save(keywordSearch);
		
		facebookPost = result.getPost();
		facebookUser = facebookPost.getUser();
		facebookUserDao.Save(facebookUser);
		facebookPostDao.Save(facebookPost);
		
		keyword = result.getKeyword();
		keywordDao.Save(keyword);
		
		resultDao.Save(result);
		
		FacebookPostKeywordResult dbResult = resultDao.GetById(result.getId());
		
		assertEquals(result.getId(), dbResult.getId());
		assertEquals(result.getKeyword(), dbResult.getKeyword());
		assertEquals(result.getPost(), dbResult.getPost());
		assertEquals(result.getSearch(), dbResult.getSearch());
		assertEquals(result.getUser(), dbResult.getUser());
		
		resultDao.Delete(result);
		keywordDao.Delete(keyword);
		facebookPostDao.Delete(facebookPost);
		facebookUserDao.Delete(facebookUser);
		keywordSearchDao.Delete(keywordSearch);
		appUserDao.Delete(appUser);
		
	}
	
	@Test
	public	void	testGetByApplicationUser()
	{
		fail( "Not implemented test" );
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getByKeywordSearchShouldThrowWhenSearchIsNull()
	{
		resultDao.getByKeywordSearch(null);
	}
	
	@Test
	public void getByKeywordSearchShouldWorkIfSearchDoesNotExist()
	{
		KeywordSearch searchDoesNotExist = new KeywordSearch();
		searchDoesNotExist.setId("aaa");
		resultDao.getByKeywordSearch(searchDoesNotExist);
	}
	
	@Test
	public void getByKeywordSearchShouldWorkCorrectly()
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
		SecureRandom random = new SecureRandom();
		for(KeywordSearch search : searches)
		{
			FacebookPostKeywordResult result = new FacebookPostKeywordResult();
			for(int resultIndex = 0; resultIndex < 5; resultIndex++)
			{
				result.setId(String.valueOf( new BigInteger(8,random )));
				result.setSearch(search);
				saveIfDoesNotExist(result, result.getId(), resultDao);
			}
		}
		KeywordSearch search = searches.get(0);
		Collection<FacebookPostKeywordResult> dbResults = resultDao.getByKeywordSearch(search);
		assertEquals(5, dbResults.size());
		
		for(FacebookPostKeywordResult result : results)
		{
			deleteIfExists(result, result.getId(), resultDao);
		}
		
		for(KeywordSearch keySearch : searches)
		{
			deleteIfExists(keySearch, keySearch.getId(), keywordSearchDao);
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
