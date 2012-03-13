package com.sones.facebook.dao.hibernate.feed;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sones.facebook.dao.feed.IStatusMessageDao;
import com.sones.facebook.dao.feed.comment.ICommentDao;
import com.sones.facebook.dao.hibernate.HibernateDaoTesterUtil;
import com.sones.facebook.dao.hibernate.feed.comment.HibernateCommentDao;
import com.sones.facebook.dao.hibernate.source.HibernateUserDao;
import com.sones.facebook.dao.source.IUserDao;
import com.sones.facebook.model.feed.StatusMessage;

public class HibernateStatusMessageDaoTester extends HibernateDaoTesterUtil
{
	private IUserDao userDao;
	private ICommentDao commentDao;
	private IStatusMessageDao statusMessageDao;
	private StatusMessage post;
	
	public HibernateStatusMessageDaoTester()
	{
		super();
	}
	
	@Before
	public void SetUp()
	{
		userDao = (HibernateUserDao) getDAOContext().getBean("userDao");
		commentDao = (HibernateCommentDao) getDAOContext().getBean("commentDao");
		statusMessageDao = (HibernateStatusMessageDao) getDAOContext().getBean("statusMessageDao");
		post = (StatusMessage) getModelContext().getBean("statusMessage");
		
		userDao.Save(post.getUser());
		commentDao.Save(post.getComments().iterator().next());
		statusMessageDao.Save(post);
	}

	@After
	public void TearDown()
	{
		commentDao.Delete(post.getComments().iterator().next());
		userDao.Delete(post.getUser());
		statusMessageDao.Delete(post);
	}
	
	@Test
	public void TestSaveGroupPostUser()
	{
		StatusMessage dbPost = statusMessageDao.GetById(post.getId());
		assertEquals(post.getUser(), dbPost.getUser());
	}
	
	@Test
	public void TestSaveGroupPostComment()
	{
		StatusMessage dbPost = statusMessageDao.GetById(post.getId());
		assertEquals(post.getComments().iterator().next(), dbPost.getComments().iterator().next());
	}
}
