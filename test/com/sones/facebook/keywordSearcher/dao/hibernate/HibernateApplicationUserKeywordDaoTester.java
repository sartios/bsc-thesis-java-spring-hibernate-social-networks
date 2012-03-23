package com.sones.facebook.keywordSearcher.dao.hibernate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.sones.facebook.keywordSearcher.dao.IApplicationUserKeywordDao;
import com.sones.facebook.keywordSearcher.dao.IKeywordDao;
import com.sones.facebook.keywordSearcher.model.ApplicationUserKeyword;
import com.sones.facebook.keywordSearcher.model.Keyword;
import com.sones.userManager.dao.IApplicationUserDao;
import com.sones.userManager.dao.hibernate.HibernateApplicationUserDao;
import com.sones.userManager.model.ApplicationUser;

public class HibernateApplicationUserKeywordDaoTester 
{
	/**
	 * The application user keyword combination that is under testing.
	 */
	private	ApplicationUserKeyword appUserKeyword;
	
	/**
	 * The application user keyword dao.
	 */
	private	IApplicationUserKeywordDao appUserKeywordDao;
	
	/**
	 * The application user who selected the keyword
	 */
	private	ApplicationUser appUser;
	
	/**
	 * The application user dao.
	 */
	private	IApplicationUserDao appUserDao;
	
	/**
	 * The keyword that was selected by the application user.
	 */
	private	Keyword keyword;
	
	/**
	 * The keyword dao.
	 */
	private	IKeywordDao	keywordDao;
	
	/**
	 * The application context provider.
	 */
	private	ApplicationContextProvider contextProvider;

	public	HibernateApplicationUserKeywordDaoTester()
	{
		contextProvider = new ApplicationContextProvider();
	}
	
	@Before
	public	void	setUp()
	{
		appUserKeyword = (ApplicationUserKeyword)contextProvider.getModelContext().getBean("appUserKeyword");
		appUserDao = (HibernateApplicationUserDao) contextProvider.getAppUserContext().getBean("applicationUserDao");
		appUserKeywordDao	=	(HibernateApplicationUserKeywordDao)contextProvider.getDaoContext().getBean("appUserKeywordDao");
		keywordDao	=	(HibernateKeywordDao)contextProvider.getDaoContext().getBean("keywordDao");
	}
	
	 @After
	 public	void	tearDown()
	 {
		 
	 }
	 
	 @Test
	 public	void	testSaveApplicationUserKeyword()
	 {
		 appUser = appUserKeyword.getId().getAppUser();
		 appUserDao.Save(appUser);
		 
		 keyword = appUserKeyword.getId().getKeyword();
		 keywordDao.Save(keyword);
		 
		 appUserKeywordDao.Save(appUserKeyword);
		 
		 ApplicationUserKeyword dbAppUserKey = appUserKeywordDao.GetById(appUserKeyword.getId());
		 
		 assertEquals(appUserKeyword.getId().getAppUser(), dbAppUserKey.getId().getAppUser());
		 assertEquals(appUserKeyword.getId().getKeyword(), dbAppUserKey.getId().getKeyword());
		 
		 appUserKeywordDao.Delete(appUserKeyword);
		 appUserDao.Delete(appUser);
		 keywordDao.Delete(keyword);
	 }
}
