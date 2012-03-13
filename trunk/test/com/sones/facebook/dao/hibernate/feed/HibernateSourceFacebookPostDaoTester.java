package com.sones.facebook.dao.hibernate.feed;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sones.facebook.dao.feed.IFacebookPostDao;
import com.sones.facebook.dao.feed.ISourceFacebookPostDao;
import com.sones.facebook.dao.hibernate.HibernateDaoTesterUtil;
import com.sones.facebook.dao.hibernate.source.HibernateSourceDao;
import com.sones.facebook.dao.hibernate.source.HibernateSourceTypeDao;
import com.sones.facebook.dao.hibernate.source.HibernateUserDao;
import com.sones.facebook.dao.source.ISourceDao;
import com.sones.facebook.dao.source.ISourceTypeDao;
import com.sones.facebook.dao.source.IUserDao;
import com.sones.facebook.model.feed.SourceFacebookPost;

public class HibernateSourceFacebookPostDaoTester extends HibernateDaoTesterUtil
{
	private SourceFacebookPost sourceFacebookPost;
	private ISourceFacebookPostDao sourceFacebookPostDao;
	private IUserDao userDao;
	private ISourceDao sourceDao;
	private IFacebookPostDao facebookPostDao;
	private ISourceTypeDao sourceTypeDao;
	
	public HibernateSourceFacebookPostDaoTester()
	{
		super();
	}
	
	@Before
	public void SetUp()
	{
		sourceFacebookPost = (SourceFacebookPost) getModelContext().getBean("sourceFacebookPost");
		sourceFacebookPostDao = (HibernateSourceFacebookPostDao) getDAOContext().getBean("sourceFacebookPostDao");
		userDao = (HibernateUserDao) getDAOContext().getBean("userDao");
		sourceDao = (HibernateSourceDao) getDAOContext().getBean("sourceDao");
		facebookPostDao = (HibernateFacebookPostDao) getDAOContext().getBean("facebookPostDao");
		sourceTypeDao = (HibernateSourceTypeDao) getDAOContext().getBean("sourceTypeDao");
		
		sourceTypeDao.Save(sourceFacebookPost.getId().getSource().getType());
		sourceDao.Save(sourceFacebookPost.getId().getSource());
		userDao.Save(sourceFacebookPost.getId().getPost().getUser());
		facebookPostDao.Save(sourceFacebookPost.getId().getPost());
		sourceFacebookPostDao.Save(sourceFacebookPost);
	}
	
	@After
	public void TearDown()
	{
		sourceFacebookPostDao.Delete(sourceFacebookPost);
		sourceDao.Delete(sourceFacebookPost.getId().getSource());
		facebookPostDao.Delete(sourceFacebookPost.getId().getPost());
		sourceTypeDao.Delete(sourceFacebookPost.getId().getSource().getType());
		userDao.Delete(sourceFacebookPost.getId().getPost().getUser());
	}
	
	@Test
	public void TestSaveSourceFacebookPostSource()
	{
		SourceFacebookPost dpPost = sourceFacebookPostDao.GetById(sourceFacebookPost.getId());
		assertEquals(sourceFacebookPost.getId().getSource(), dpPost.getId().getSource());
	}
	
	@Test
	public void TestSaveSourceFacebookPostFacebookPost()
	{
		SourceFacebookPost dpPost = sourceFacebookPostDao.GetById(sourceFacebookPost.getId());
		assertEquals(sourceFacebookPost.getId().getPost(), dpPost.getId().getPost());
	}
}
