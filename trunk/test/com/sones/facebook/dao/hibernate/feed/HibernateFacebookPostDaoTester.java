package com.sones.facebook.dao.hibernate.feed;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sones.facebook.dao.feed.IFacebookPostDao;
import com.sones.facebook.dao.feed.comment.ICommentDao;
import com.sones.facebook.dao.hibernate.HibernateDaoTesterUtil;
import com.sones.facebook.dao.hibernate.feed.comment.HibernateCommentDao;
import com.sones.facebook.dao.hibernate.source.HibernateUserDao;
import com.sones.facebook.dao.source.IUserDao;
import com.sones.facebook.model.feed.FacebookPost;

public class HibernateFacebookPostDaoTester extends HibernateDaoTesterUtil
{
	private IUserDao userDao;
	private ICommentDao commentDao;
	private IFacebookPostDao facebookPostDao;
	private FacebookPost post;
	private final Logger _LOGGER;
	
	public HibernateFacebookPostDaoTester()
	{
		super();
		_LOGGER = Logger.getLogger(HibernateFacebookPostDaoTester.class);
	}
	
	@Before
	public void SetUp()
	{
		userDao = (HibernateUserDao) getDAOContext().getBean("userDao");
		commentDao = (HibernateCommentDao) getDAOContext().getBean("commentDao");
		facebookPostDao = (HibernateFacebookPostDao) getDAOContext().getBean("facebookPostDao");
		post = (FacebookPost) getModelContext().getBean("facebookPost");
		
		userDao.Save(post.getUser());
		commentDao.Save(post.getComments().iterator().next());
		facebookPostDao.Save(post);
	}

	
	@After
	public void TearDown()
	{
		commentDao.Delete(post.getComments().iterator().next());
		userDao.Delete(post.getUser());
		facebookPostDao.Delete(post);
	}
	
	@Test
	public void TestSaveFacebookPostUser()
	{
		FacebookPost dbPost = facebookPostDao.GetById(post.getId());
		assertEquals(post.getUser(), dbPost.getUser());
	}
	
	@Test
	public void TestSaveFacebookPostComment()
	{
		FacebookPost dbPost = facebookPostDao.GetById(post.getId());
		assertEquals(post.getComments().iterator().next(), dbPost.getComments().iterator().next());
	}
	
	@Test
	public void TestSaveFacebookPostFromSameUser()
	{
		FacebookPost post2 = new FacebookPost();
		post2.setId("000000001");
		post2.setUser(post.getUser());
		facebookPostDao.Save(post2);
		
		FacebookPost dbPost1 = facebookPostDao.GetById(post.getId());
		FacebookPost dbPost2 = facebookPostDao.GetById(post2.getId());
		assertEquals("Post 1 user id is empty", post.getUser().getId(), dbPost1.getUser().getId());
		_LOGGER.warn("Post 1 user id: "+dbPost1.getUser().getId());
		assertEquals("Post 2 user id is empty", post2.getUser().getId(), dbPost2.getUser().getId());
		_LOGGER.warn("Post 2 user id: "+dbPost2.getUser().getId());
		facebookPostDao.Delete(post2);
	}
}
