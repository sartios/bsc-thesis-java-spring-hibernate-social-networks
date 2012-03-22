package com.sones.facebook.keywordSearcher.dao.hibernate;

import java.util.Iterator;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
import com.sones.userManager.dao.IApplicationUserDao;
import com.sones.userManager.dao.hibernate.HibernateApplicationUserDao;

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
}
