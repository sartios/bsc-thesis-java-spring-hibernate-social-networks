package com.sones.facebook.dao.hibernate.source;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static	org.junit.Assert.*;

import com.sones.facebook.dao.source.IApplicationUserSourceDao;
import com.sones.facebook.dao.source.ISourceDao;
import com.sones.facebook.dao.source.ISourceTypeDao;
import com.sones.facebook.model.source.ApplicationUserSource;
import com.sones.facebook.model.source.Source;
import com.sones.facebook.model.source.SourceType;
import com.sones.userManager.dao.IApplicationUserDao;
import com.sones.userManager.dao.hibernate.HibernateApplicationUserDao;
import com.sones.userManager.model.ApplicationUser;

public class HibernateApplicationUserSourceDaoTester 
{
	/**
	 * The application user source combination that is under tested.
	 */
	private	ApplicationUserSource appUserSource;
	
	/**
	 * The application user source dao.
	 */
	private	IApplicationUserSourceDao	appUserSourceDao;
	
	/**
	 * The spring application context provider.
	 */
	private	ApplicationContext context;
	
	/**
	 * The application user who selected the source.
	 */
	private	ApplicationUser appUser;
	
	/**
	 * The application user dao.
	 */
	private IApplicationUserDao appUserDao;
	
	/**
	 * The source that the application had selected.
	 */
	private	Source source;
	
	/**
	 * The source dao.
	 */
	private	ISourceDao sourceDao;
	
	/**
	 * The type of the selected source.
	 */
	private	SourceType sourceType;
	
	/**
	 * The source type dao.
	 */
	private	ISourceTypeDao sourceTypeDao;
	
	/**
	 * Initializes the test and the context provider.
	 */
	public HibernateApplicationUserSourceDaoTester()
	{
		context = new ClassPathXmlApplicationContext("SourceSelector/spring-source-selector-nu.xml");
	}
	
	@Before
	public	void	setUp()
	{
		appUserSource	=	(ApplicationUserSource) context.getBean("appUserSource");
		appUserSourceDao	=	(HibernateApplicationUserSourceDao)context.getBean("appUserSourceDao");
		appUserDao	=	(HibernateApplicationUserDao)context.getBean("appUserDao");
		sourceDao	=	(HibernateSourceDao)context.getBean("sourceDao");
		sourceTypeDao	=	(HibernateSourceTypeDao)context.getBean("sourceTypeDao");
	}
	
	@After
	public	void	tearDown()
	{
	}
	
	@Test
	public	void	testSaveApplicationUserSource()
	{
		appUser	=	appUserSource.getId().getAppUser();
		appUserDao.Save(appUser);
		
		source	=	appUserSource.getId().getSource();
		sourceType = source.getType();
		sourceTypeDao.Save(sourceType);
		sourceDao.Save(source);
		
		appUserSourceDao.Save(appUserSource);
		
		ApplicationUserSource dbSource = appUserSourceDao.GetById(appUserSource.getId());
		
		assertEquals(appUserSource.getId(), dbSource.getId());
		
		appUserSourceDao.Delete(appUserSource);
		appUserDao.Delete(appUser);
		sourceDao.Delete(source);
		sourceTypeDao.Delete(sourceType);
	}
	
	@Test
	public	void	testGetApplicationUserSourcesByUser()
	{
		appUser	=	appUserSource.getId().getAppUser();
		source	=	appUserSource.getId().getSource();
		sourceType	=	source.getType();
		List<ApplicationUserSource> userSources = new ArrayList<ApplicationUserSource>();
		userSources.add( appUserSource );
		for( int index=2; index<5; index++ )
		{
			Source	genSource	=	new	Source();
			genSource.setId( String.valueOf( index ) );
			genSource.setType( sourceType );
			ApplicationUserSource	genUserSource	=	new	ApplicationUserSource(genSource,appUser);
			userSources.add( genUserSource );
		}
		appUserDao.Save( appUser );
		sourceTypeDao.Save( sourceType );
		for( int index = 0; index < userSources.size(); index++ )
		{
			ApplicationUserSource	genAppUserSource	=	userSources.get( index );
			sourceDao.Save( genAppUserSource.getId().getSource() );
			appUserSourceDao.Save( genAppUserSource );
		}
		Iterable<ApplicationUserSource>	dbUserSources	=	appUserSourceDao.getApplicationUserSourcesByUser( appUser );
		assertNotNull( dbUserSources );
		List	list	=	(List) dbUserSources;
		assertEquals(userSources.size(), list.size());
		
		for( int index = 0; index < userSources.size(); index++ )
		{
			ApplicationUserSource	genAppUserSource	=	userSources.get( index );
			appUserSourceDao.Delete( genAppUserSource );
			sourceDao.Delete( genAppUserSource.getId().getSource() );
		}
		sourceTypeDao.Delete( sourceType );
		appUserDao.Delete( appUser );
	}
	
	@Test(expected=IllegalArgumentException.class)
	public	void	testGetApplicationUserSourcesByUserThrowsException()
	{
		appUserSourceDao.getApplicationUserSourcesByUser(null);
	}
}
