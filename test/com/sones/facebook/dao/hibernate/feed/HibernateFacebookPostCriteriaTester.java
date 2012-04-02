package com.sones.facebook.dao.hibernate.feed;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.dao.IGenericDao;
import com.sones.facebook.dao.feed.IFacebookPostCriteriaDao;
import com.sones.facebook.dao.feed.IFacebookPostDao;
import com.sones.facebook.model.feed.FacebookPost;
import com.sones.facebook.model.feed.FacebookPostCriteria;
import com.sones.facebook.publicsource.dao.ICriteriaDao;
import com.sones.facebook.publicsource.dao.hibernate.HibernateCriteriaDao;
import com.sones.facebook.publicsource.model.Criteria;

public class HibernateFacebookPostCriteriaTester 
{
	private	FacebookPost	post;
	private	Criteria	criteria;
	private	FacebookPostCriteria	testedObject;
	
	private	IFacebookPostDao	postDao;
	private	ICriteriaDao	criteriaDao;
	private	IFacebookPostCriteriaDao	postCriteriaDao;
	private	ApplicationContext	context;
	
	public	HibernateFacebookPostCriteriaTester()
	{
		context	=	new	ClassPathXmlApplicationContext( "spring-facebook-dao.xml" );
		postDao	=	( HibernateFacebookPostDao )	context.getBean( "postDao" );
		criteriaDao	=	( HibernateCriteriaDao )	context.getBean( "criteriaDao" );
		postCriteriaDao	=	( HibernateFacebookPostCriteriaDao )	context.getBean( "postCriteriaDao" );
	}
	
	@Before
	public	void	setup()
	{
		post	=	new	FacebookPost();
		post.setId( "1" );
		
		criteria	=	new	Criteria();
		criteria.setId( "1" );
		
		testedObject	=	new	FacebookPostCriteria(post, criteria);
	}
	
	@After
	public	void	teardown()
	{
		deleteIfExists( testedObject.getId(), postCriteriaDao );
		deleteIfExists( post.getId(), postDao );
		deleteIfExists( criteria.getId(), criteriaDao );
	}
	
	@Test
	public	void	testSaveFacebookPostCriteria()
	{
		postCriteriaDao.Save( testedObject );
		FacebookPostCriteria	postCriteria	=	postCriteriaDao.GetById( testedObject.getId() );
		
		assertNotNull(postCriteria);
		assertEquals( testedObject , postCriteria );
	}
	
	private	void	deleteIfExists( Object id, IGenericDao dao )
	{
		Object	model	=	dao.GetById( id );
		if( model != null )
		{
			dao.Delete( model );
		}
	}
}
