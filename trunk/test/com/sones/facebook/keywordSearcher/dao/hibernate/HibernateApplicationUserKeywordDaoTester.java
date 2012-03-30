package com.sones.facebook.keywordSearcher.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.sones.facebook.keywordSearcher.dao.IApplicationUserKeywordDao;
import com.sones.facebook.keywordSearcher.dao.IKeywordDao;
import com.sones.facebook.keywordSearcher.model.ApplicationUserKeyword;
import com.sones.facebook.keywordSearcher.model.Keyword;
import com.sones.usermanager.dao.hibernate.HibernateApplicationUserDao;
import com.sones.usermanager.dao.IApplicationUserDao;
import com.sones.usermanager.model.ApplicationUser;

public class HibernateApplicationUserKeywordDaoTester 
{
	/**
	 * The application user keyword dao.
	 */
	private	IApplicationUserKeywordDao appUserKeywordDao;
	
	/**
	 * The application user dao.
	 */
	private	IApplicationUserDao appUserDao;
	
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
		appUserDao = (HibernateApplicationUserDao) contextProvider.getAppUserContext().getBean("applicationUserDao");
		appUserKeywordDao	=	(HibernateApplicationUserKeywordDao)contextProvider.getDaoContext().getBean("appUserKeywordDao");
		keywordDao	=	(HibernateKeywordDao)contextProvider.getDaoContext().getBean("keywordDao");
	}
	
	 @After
	 public	void	tearDown()
	 {
			appUserDao = null;
			appUserKeywordDao	=	null;
			keywordDao	 =	null;
	 }
	 
	 @Test
	 public	void	testSaveApplicationUserKeyword()
	 {
		/* appUser = appUserKeyword.getId().getAppUser();
		 appUserDao.Save(appUser);*/
		 
		 ApplicationUser	appUserModel	=	SaveUserAndReturn();
		 
		/* keyword = appUserKeyword.getId().getKeyword();
		 keywordDao.Save(keyword);*/
		 
		 Iterable< Keyword >	keywords	=	SaveKeywordsAndReturn();
		 
		 /*appUserKeywordDao.Save(appUserKeyword);*/
		 
		 Iterable< ApplicationUserKeyword >	appUserKeywords	=	SaveUserKeywordsAndReturn(keywords, appUserModel);
		 ApplicationUserKeyword	appUserKeyword	=	appUserKeywords.iterator().next();
		 ApplicationUserKeyword dbAppUserKey = appUserKeywordDao.GetById(appUserKeyword.getId());
		 
		 assertEquals(appUserKeyword.getId().getAppUser(), dbAppUserKey.getId().getAppUser());
		 assertEquals(appUserKeyword.getId().getKeyword(), dbAppUserKey.getId().getKeyword());
		 
		 DeleteUserKeywords(appUserKeywords);
		 DeleteKeywords(keywords);
		 appUserDao.Delete(appUserModel);
	}
	 
	 @Test
	 public	void	testGetByApplicationUser()
	 {
		 ApplicationUser	userModel	=	SaveUserAndReturn();
		 List< Keyword >	keywords	=	(List<Keyword>) SaveKeywordsAndReturn();
		 List< ApplicationUserKeyword >	userKeywords	=	(List<ApplicationUserKeyword>) SaveUserKeywordsAndReturn( keywords, userModel );
		 
		 List< ApplicationUserKeyword >	dbResults	=	(List<ApplicationUserKeyword>) appUserKeywordDao.getByApplicationUser( userModel );
	
		 assertNotNull( dbResults );
		 assertEquals( 4 , dbResults.size() );
		 
		 DeleteUserKeywords( userKeywords );
		 DeleteKeywords( keywords );
		 appUserDao.Delete( userModel );
	 }
	 
		private	ApplicationUser	SaveUserAndReturn()
		{
			ApplicationUser	appUser	=	new	ApplicationUser();
			appUser.setId( "1" );
			appUserDao.Save( appUser );
			return	appUser;
		}
		
		private	Iterable< Keyword >	SaveKeywordsAndReturn()
		{
			List< Keyword >	keywords	=	new	ArrayList< Keyword >();
			for( int keywordIndex = 1 ; keywordIndex < 5 ; keywordIndex++ )
			{
				Keyword	keyword	=	new	Keyword();
				keyword.setId( String.valueOf( keywordIndex ) );
				keyword.setValue( "KeywordValue " + keywordIndex );
				keywordDao.Save( keyword );
				keywords.add( keyword );
			}
			return	keywords;
		}
		
		private	Iterable< ApplicationUserKeyword >	SaveUserKeywordsAndReturn( Iterable< Keyword > keywords, ApplicationUser appUser )
		{
			List< ApplicationUserKeyword >	appUserKeywords	=	new	ArrayList<ApplicationUserKeyword>();
			for( Keyword keyword : keywords )
			{
				ApplicationUserKeyword	appUserKeyword	=	new	ApplicationUserKeyword( keyword, appUser );
				appUserKeywords.add( appUserKeyword );
				appUserKeywordDao.Save( appUserKeyword );
			}
			return	appUserKeywords;
		}
		
		private	void	DeleteUserKeywords( Iterable< ApplicationUserKeyword > keywords )
		{
			for( ApplicationUserKeyword keyword : keywords )
			{
				appUserKeywordDao.Delete( keyword );
			}
		}
		
		private	void	DeleteKeywords( Iterable< Keyword > keywords )
		{
			for( Keyword keyword : keywords )
			{
				keywordDao.Delete( keyword );
			}
		}
}
