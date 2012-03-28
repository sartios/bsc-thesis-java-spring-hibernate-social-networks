package com.sones.facebook.keywordSearcher.logic.keyword.retriever;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.facebook.keywordSearcher.dao.IApplicationUserKeywordDao;
import com.sones.facebook.keywordSearcher.dao.IKeywordDao;
import com.sones.facebook.keywordSearcher.dao.hibernate.HibernateApplicationUserKeywordDao;
import com.sones.facebook.keywordSearcher.dao.hibernate.HibernateKeywordDao;
import com.sones.facebook.keywordSearcher.model.ApplicationUserKeyword;
import com.sones.facebook.keywordSearcher.model.Keyword;
import com.sones.sharedDto.facebook.keywordSearcher.KeywordSearchDto;
import com.sones.userManager.dao.IApplicationUserDao;
import com.sones.userManager.dao.hibernate.HibernateApplicationUserDao;
import com.sones.userManager.model.ApplicationUser;

public class KeywordRetrieverTester 
{
	
	private	IApplicationUserKeywordRetriever	keywordRetriever;
	private	IKeywordDao	keywordDao;
	private	IApplicationUserKeywordDao	appUserKeywordDao;
	private	ApplicationContext	context;
	private	IApplicationUserDao	appUserDao;
	
	
	public	KeywordRetrieverTester()
	{
		context	=	new	ClassPathXmlApplicationContext( "KeywordSearcher/spring-keywordRetriever-logic-nu.xml" );
	}
	
	@Before
	public	void	setUp()
	{
		keywordRetriever	=	( KeywordRetriever )context.getBean( "keywordRetriever" );
		appUserKeywordDao	=	( HibernateApplicationUserKeywordDao )context.getBean( "appUserKeywordDao" );
		keywordDao	=	( HibernateKeywordDao )context.getBean( "keywordDao" );
		appUserDao	=	( HibernateApplicationUserDao )context.getBean( "appUserDao" );
	}
	
	@After
	public	void	tearDown()
	{
		keywordRetriever	=	null;
		appUserKeywordDao	=	null;
		keywordDao	=	null;
	}
	
	@Test
	public	void	testGetApplicationUserKeywordsThereKeywordsButNotForThisUser()
	{
		ApplicationUser	appUserWithOutKeywords	=	new	ApplicationUser();
		appUserWithOutKeywords.setId( "123456789" );
		appUserDao.Save( appUserWithOutKeywords );
		ApplicationUser	appUser	=	SaveUserAndReturn();
		Iterable< Keyword >	keywords	=	SaveKeywordsAndReturn();
		List< ApplicationUserKeyword >	appUserKeywords	=	(List<ApplicationUserKeyword>) SaveUserKeywordsAndReturn( keywords, appUser );
		Set< KeywordSearchDto >	serviceResults	=	(Set<KeywordSearchDto>) keywordRetriever.getApplicationUserKeywords( appUserWithOutKeywords );
		assertNull( serviceResults );
		DeleteUserKeywords( appUserKeywords );
		DeleteKeywords( keywords );
		appUserDao.Delete( appUser );
	}
	
	@Test
	public	void	testGetApplicationUserKeywordsThereAreKeywordsForTheUser()
	{
		ApplicationUser	appUser	=	SaveUserAndReturn();
		Iterable< Keyword >	keywords	=	SaveKeywordsAndReturn();
		List< ApplicationUserKeyword >	appUserKeywords	=	(List<ApplicationUserKeyword>) SaveUserKeywordsAndReturn( keywords, appUser );
		Set< KeywordSearchDto >	serviceResults	=	(Set<KeywordSearchDto>) keywordRetriever.getApplicationUserKeywords( appUser );
		assertEquals( appUserKeywords.size(), serviceResults.size());
		DeleteUserKeywords( appUserKeywords );
		DeleteKeywords( keywords );
		appUserDao.Delete( appUser );
	}
	
	@Test( expected = IllegalArgumentException.class )
	public	void	testGetApplicationUserKeywordsNullApplicationUser()
	{
		keywordRetriever.getApplicationUserKeywords( null );
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
