package com.sones.facebook.placemanager.dao.hibernate;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.facebook.placemanager.dao.IPlaceCategoryDao;
import com.sones.facebook.placemanager.model.PlaceCategory;

public class HibernatePlaceCategoryDaoTester 
{
	private	PlaceCategory	category;
	private	IPlaceCategoryDao	categoryDao;
	private	ApplicationContext	context;
	
	public	HibernatePlaceCategoryDaoTester()
	{
		context	=	new	ClassPathXmlApplicationContext( "PlaceManager/spring-placemanager-dao-nu.xml" );
		categoryDao	=	( HibernatePlaceCategoryDao )	context.getBean( "categoryDao" );
	}
	
	@Before
	public	void	setUp()
	{
		category	=	new	PlaceCategory();
		category.setId( "1" );
		category.setDescription( "Local Business" );
	}
	
	@After
	public	void	tearDown()
	{
		category	=	null;
	}
	
	@Test
	public	void	testSavePlaceCategory()
	{
		categoryDao.Save( category );
		PlaceCategory	dbCategory	=	categoryDao.GetById( category.getId() );
		
		assertNotNull( dbCategory );
		assertEquals( category, dbCategory );
		
		categoryDao.Delete( category );
	}
	
	@Test( expected = IllegalArgumentException.class )
	public void testGetByDescriptionNullDescription()
	{
		PlaceCategory	dbCategory	=	categoryDao.GetByDescription( null );
	}
	
	@Test
	public	void	testGetByDescription()
	{
		categoryDao.Save( category );
		PlaceCategory	dbCategory	=	categoryDao.GetByDescription( category.getDescription() );
		
		assertNotNull( dbCategory );
		assertEquals( category, dbCategory );
		
		categoryDao.Delete( category );
	}
}
