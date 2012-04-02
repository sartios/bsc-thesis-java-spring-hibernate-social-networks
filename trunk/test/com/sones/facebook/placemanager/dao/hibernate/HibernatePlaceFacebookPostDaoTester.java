package com.sones.facebook.placemanager.dao.hibernate;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.dao.IGenericDao;
import com.sones.facebook.dao.feed.IFacebookPostDao;
import com.sones.facebook.dao.hibernate.feed.HibernateFacebookPostDao;
import com.sones.facebook.model.feed.FacebookPost;
import com.sones.facebook.placemanager.dao.IPlaceDao;
import com.sones.facebook.placemanager.dao.IPlaceFacebookPostDao;
import com.sones.facebook.placemanager.model.Place;
import com.sones.facebook.placemanager.model.PlaceFacebookPost;
import com.sones.facebook.placemanager.model.PlaceFacebookPostId;

public class HibernatePlaceFacebookPostDaoTester 
{

	private	Place	place;
	private	FacebookPost	post;
	private	PlaceFacebookPost	placePost;
	
	private	IPlaceDao	placeDao;
	private	IFacebookPostDao	postDao;
	private	IPlaceFacebookPostDao	placePostDao;
	
	private	ApplicationContext	context;
	
	public	HibernatePlaceFacebookPostDaoTester()
	{
		context	=	new	ClassPathXmlApplicationContext( "PlaceManager/spring-placemanager-dao-nu.xml" );
		
		placeDao	=	( HibernatePlaceDao )	context.getBean( "placeDao" );
		postDao	=	( HibernateFacebookPostDao )	context.getBean( "postDao" );
		placePostDao	=	( HibernatePlaceFacebookPostDao )	context.getBean( "placePostDao" );
	}
	
	@Before
	public	void	setUp()
	{
		place	=	new	Place();
		place.setId( "1" );
		
		post	=	new	FacebookPost();
		post.setId( "1" );
		
		placePost	=	new	PlaceFacebookPost( place , post );
	}
	
	@After
	public	void	tearDown()
	{
		deleteIfExists( placePost.getId(), placePostDao );
		deleteIfExists( place.getId(), placeDao );
		deleteIfExists( post.getId(), postDao );
	}

	@Test
	public	void	testSavePlacePost()
	{
		placeDao.Save( place );
		postDao.Save( post );
		placePostDao.Save( placePost );
		
		PlaceFacebookPost	dbPlacePost	=	placePostDao.GetById( placePost.getId() );
		
		assertNotNull( dbPlacePost );
		assertEquals( placePost , dbPlacePost );
	}
	
	private void deleteIfExists( Object id, IGenericDao dao )
	{
		Object	model	=	dao.GetById( id );
		if( model != null )
		{
			dao.Delete( model );
		}
	}
}
