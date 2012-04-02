package com.sones.facebook.usermanager.dao.hibernate;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.dao.IGenericDao;
import com.sones.facebook.publicsource.dao.ICriteriaDao;
import com.sones.facebook.publicsource.dao.hibernate.HibernateCriteriaDao;
import com.sones.facebook.publicsource.model.Criteria;
import com.sones.facebook.usermanager.dao.IApplicationUserPlaceCriteriaDao;
import com.sones.facebook.usermanager.model.ApplicationUserPlaceCriteria;
import com.sones.facebook.usermanager.model.ApplicationUserPlaceCriteriaId;
import	com.sones.usermanager.model.ApplicationUser;
import	com.sones.usermanager.dao.hibernate.HibernateApplicationUserDao;
import	com.sones.usermanager.dao.IApplicationUserDao;


public class HibernateApplicationUserPlaceCriteriaTester 
{
	private	ApplicationUser	appUser;
	private	Criteria	criteria;
	private	ApplicationUserPlaceCriteria	appUserCriteria;
	
	private	IApplicationUserDao	appUserDao;
	private	ICriteriaDao	criteriaDao;
	private	IApplicationUserPlaceCriteriaDao	appUserCriteriaDao;
	private	ApplicationContext	context;
	
	public	HibernateApplicationUserPlaceCriteriaTester()
	{
		context	=	new	ClassPathXmlApplicationContext( "UserManager/spring-usermanager-dao-nu.xml" );
		
		appUserDao	=	( HibernateApplicationUserDao )	context.getBean( "appUserDao" );
		criteriaDao	=	( HibernateCriteriaDao )	context.getBean( "criteriaDao" );
		appUserCriteriaDao	=	( HibernateApplicationUserPlaceCriteriaDao )	context.getBean( "appUserCriteriaDao" );
	}
	
	@Before
	public	void	setUp()
	{
		appUser	=	new	ApplicationUser();
		appUser.setId( "1" );
		
		criteria	=	new	Criteria();
		criteria.setId( "1" );
		
		appUserCriteria	=	new	ApplicationUserPlaceCriteria(appUser, criteria);
	}
	
	@After
	public	void	tearDown()
	{
		deleteIfExists(appUserCriteria.getId(), appUserCriteriaDao);
		deleteIfExists(appUser.getId(), appUserDao);
		deleteIfExists(criteria.getId(), criteriaDao);
	}
	
	@Test
	public	void	testSaveApplicationUserCriteria()
	{
		appUserDao.Save( appUser );
		criteriaDao.Save( criteria );
		appUserCriteriaDao.Save( appUserCriteria );
		
		ApplicationUserPlaceCriteria	dbAppUserCriteria	=	appUserCriteriaDao.GetById( appUserCriteria.getId() );
		
		assertNotNull( dbAppUserCriteria );
		assertEquals( appUserCriteria , dbAppUserCriteria );
	}
	
	private void deleteIfExists(Object object, IGenericDao dao) 
	{
		Object	model	=	dao.GetById( object );
		if( model != null )
		{
			dao.Delete( model );
		}
	}
}
