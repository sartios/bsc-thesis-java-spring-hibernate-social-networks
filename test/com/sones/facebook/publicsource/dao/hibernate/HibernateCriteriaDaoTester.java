package com.sones.facebook.publicsource.dao.hibernate;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.facebook.publicsource.dao.ICriteriaDao;
import com.sones.facebook.publicsource.model.Criteria;

public class HibernateCriteriaDaoTester 
{
	private	Criteria	criteria;
	private	ICriteriaDao	criteriaDao;
	private	ApplicationContext	context;
	
	public	HibernateCriteriaDaoTester()
	{
		context	=	new	ClassPathXmlApplicationContext( "PlaceManager/spring-placemanager-dao-nu.xml" );
		criteriaDao	=	( HibernateCriteriaDao )	context.getBean( "criteriaDao" );
	}
	
	@Before
	public	void	setUp()
	{
		criteria	=	new	Criteria();
		criteria.setId( "1" );
		criteria.setValue( "coffee" );
	}
	
	@After
	public	void	tearDown()
	{
		criteria	=	null;
	}
	
	@Test
	public	void	testSaveCriteria()
	{
		criteriaDao.Save( criteria );
		Criteria	dbCriteria	=	criteriaDao.GetById( criteria.getId() );
		
		assertNotNull( dbCriteria );
		assertEquals( criteria , dbCriteria );
		
		criteriaDao.Delete( criteria );
	}
}
