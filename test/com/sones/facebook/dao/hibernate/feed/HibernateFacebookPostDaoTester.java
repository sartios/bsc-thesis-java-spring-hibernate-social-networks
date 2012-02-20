package com.sones.facebook.dao.hibernate.feed;

import static org.junit.Assert.assertEquals;

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
	
	public HibernateFacebookPostDaoTester()
	{
		super();
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
}
