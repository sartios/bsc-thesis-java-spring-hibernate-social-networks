package com.sones.facebook.dao.hibernate.feed;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sones.facebook.dao.feed.ILinkDao;
import com.sones.facebook.dao.feed.comment.ICommentDao;
import com.sones.facebook.dao.hibernate.HibernateDaoTesterUtil;
import com.sones.facebook.dao.hibernate.feed.comment.HibernateCommentDao;
import com.sones.facebook.dao.hibernate.source.HibernateUserDao;
import com.sones.facebook.dao.source.IUserDao;
import com.sones.facebook.model.feed.Link;

public class HibernateLinkDaoTester extends HibernateDaoTesterUtil
{
	private IUserDao userDao;
	private ICommentDao commentDao;
	private ILinkDao linkDao;
	private Link post;
	
	public HibernateLinkDaoTester()
	{
		super();
	}
	
	@Before
	public void SetUp()
	{
		userDao = (HibernateUserDao) getDAOContext().getBean("userDao");
		commentDao = (HibernateCommentDao) getDAOContext().getBean("commentDao");
		linkDao = (HibernateLinkDao) getDAOContext().getBean("linkDao");
		post = (Link) getModelContext().getBean("link");
		
		userDao.Save(post.getUser());
		commentDao.Save(post.getComments().iterator().next());
		linkDao.Save(post);
	}

	@After
	public void TearDown()
	{
		commentDao.Delete(post.getComments().iterator().next());
		userDao.Delete(post.getUser());
		linkDao.Delete(post);
	}
	
	@Test
	public void TestSaveGroupPostUser()
	{
		Link dbPost = linkDao.GetById(post.getId());
		assertEquals(post.getUser(), dbPost.getUser());
	}
	
	@Test
	public void TestSaveGroupPostComment()
	{
		Link dbPost = linkDao.GetById(post.getId());
		assertEquals(post.getComments().iterator().next(), dbPost.getComments().iterator().next());
	}
}
