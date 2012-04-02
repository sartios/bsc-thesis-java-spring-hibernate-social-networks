package com.sones.facebook.placemanager.dao.hibernate;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.dao.IGenericDao;
import com.sones.facebook.placemanager.dao.IPlaceCriteriaDao;
import com.sones.facebook.placemanager.dao.IPlaceDao;
import com.sones.facebook.placemanager.model.Place;
import com.sones.facebook.placemanager.model.PlaceCriteria;
import com.sones.facebook.publicsource.dao.ICriteriaDao;
import com.sones.facebook.publicsource.dao.hibernate.HibernateCriteriaDao;
import com.sones.facebook.publicsource.model.Criteria;

public class HibernatePlaceCriteriaDaoTester
{
	private	Criteria	criteria;
	private	Place	place;
	private	ICriteriaDao	criteriaDao;
	private	IPlaceDao	placeDao;
	private	ApplicationContext	context;
	private	PlaceCriteria	placeCriteria;
	private	IPlaceCriteriaDao	placeCriteriaDao;
	
	public	HibernatePlaceCriteriaDaoTester()
	{
		context	=	new	ClassPathXmlApplicationContext( "PlaceManager/spring-placemanager-dao-nu.xml" );
		criteriaDao	=	( HibernateCriteriaDao )	context.getBean( "criteriaDao" );
		placeDao	=	( HibernatePlaceDao )	context.getBean( "placeDao" );
		placeCriteriaDao	=	( HibernatePlaceCriteriaDao )	context.getBean( "placeCriteriaDao" );
	}
	
	@Before
	public	void	setUp()
	{
		criteria	=	new	Criteria();
		criteria.setId( "1" );
		criteria.setValue( "coffee" );
		
		place	=	new	Place();
		place.setId( "1" );
		place.setName( "Vogue" );
		
		placeCriteria	=	new	PlaceCriteria(place, criteria);
	}
	
	@After
	public	void	tearDown()
	{
		deleteIfExists( placeCriteria.getId() , placeCriteriaDao );
		deleteIfExists( place.getId() , placeDao );
		deleteIfExists( criteria.getId() , criteriaDao );

	}
	
	@Test
	public	void	testSavePlaceCriteria()
	{
		placeDao.Save( place );
		criteriaDao.Save( criteria );
		placeCriteriaDao.Save( placeCriteria );
		
		PlaceCriteria	dbPlaceCriteria	=	placeCriteriaDao.GetById( placeCriteria.getId() );
		assertNotNull( dbPlaceCriteria );
		assertEquals( placeCriteria, dbPlaceCriteria );
		
		placeCriteriaDao.Delete(placeCriteria);
		placeDao.Delete(place);
		criteriaDao.Delete(criteria);
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
