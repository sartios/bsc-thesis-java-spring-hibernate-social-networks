package com.sones.facebook.placemanager.dao.hibernate;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.facebook.placemanager.dao.IPlaceCategory;
import com.sones.facebook.placemanager.dao.IPlaceDao;
import com.sones.facebook.placemanager.model.Place;
import com.sones.facebook.placemanager.model.PlaceCategory;

public class HibernatePlaceDaoTester 
{
	private	Place	place;
	private	PlaceCategory	category;
	private	IPlaceDao	placeDao;
	private	IPlaceCategory	categoryDao;
	private	ApplicationContext	context;
	
	public	HibernatePlaceDaoTester()
	{
		context	=	new	ClassPathXmlApplicationContext( "PlaceManager/spring-placemanager-dao-nu.xml" );
		categoryDao	=	( HibernatePlaceCategoryDao )	context.getBean( "categoryDao" );
		placeDao	=	( HibernatePlaceDao )	context.getBean( "placeDao" );
	}
	
	@Before
	public	void	setUp()
	{
		category	=	new	PlaceCategory();
		category.setId( "1" );
		category.setDescription( "Local business" );
		
		place	=	new	Place();
		place.setId( "1" );
		place.setName( "Sarti" );
		place.setNumberOfCheckins( 1200 );
		place.setLatitude( "10025" );
		place.setLongitude( "5220" );
		place.setCategory( category );
	}
	
	@After
	public	void	tearDown()
	{
		category	=	null;
		place	=	null;
	}
	
	@Test
	public	void	testSavePlace()
	{
		categoryDao.Save( category );
		placeDao.Save( place );
		
		Place	dbPlace	=	placeDao.GetById( place.getId() );
		
		assertNotNull( dbPlace );
		assertEquals( place.getCategory() , dbPlace.getCategory() );
		assertEquals( place.getId() , dbPlace.getId() );
		assertEquals( place.getLatitude() , dbPlace.getLatitude() );
		assertEquals( place.getLongitude() , dbPlace.getLongitude() );
		assertEquals( place.getName() , dbPlace.getName() );
		assertEquals( place.getNumberOfCheckins() , dbPlace.getNumberOfCheckins() );
		
		placeDao.Delete(place);
		categoryDao.Delete(category);
	}
}
