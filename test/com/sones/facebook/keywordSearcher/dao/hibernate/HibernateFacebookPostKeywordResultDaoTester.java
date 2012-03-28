package com.sones.facebook.keywordSearcher.dao.hibernate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
import com.sones.userManager.dao.IApplicationUserDao;
import com.sones.userManager.dao.hibernate.HibernateApplicationUserDao;
import com.sones.userManager.model.ApplicationUser;

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
	
}
