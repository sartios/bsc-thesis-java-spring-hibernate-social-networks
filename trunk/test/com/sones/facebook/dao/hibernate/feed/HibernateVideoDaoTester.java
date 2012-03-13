package com.sones.facebook.dao.hibernate.feed;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sones.facebook.dao.feed.IStatusMessageDao;
import com.sones.facebook.dao.feed.IVideoDao;
import com.sones.facebook.dao.feed.comment.ICommentDao;
import com.sones.facebook.dao.hibernate.HibernateDaoTesterUtil;
import com.sones.facebook.dao.hibernate.feed.comment.HibernateCommentDao;
import com.sones.facebook.dao.hibernate.source.HibernateUserDao;
import com.sones.facebook.dao.source.IUserDao;
import com.sones.facebook.model.feed.StatusMessage;
import com.sones.facebook.model.feed.Video;

public class HibernateVideoDaoTester  extends HibernateDaoTesterUtil
{
	private IUserDao userDao;
	private ICommentDao commentDao;
	private IVideoDao videoDao;
	private Video post;
	
	public HibernateVideoDaoTester()
	{
		super();
	}
	
	@Before
	public void SetUp()
	{
		userDao = (HibernateUserDao) getDAOContext().getBean("userDao");
		commentDao = (HibernateCommentDao) getDAOContext().getBean("commentDao");
		videoDao = (HibernateVideoDao) getDAOContext().getBean("videoDao");
		post = (Video) getModelContext().getBean("video");
		
		userDao.Save(post.getUser());
		commentDao.Save(post.getComments().iterator().next());
		videoDao.Save(post);
	}

	@After
	public void TearDown()
	{
		commentDao.Delete(post.getComments().iterator().next());
		userDao.Delete(post.getUser());
		videoDao.Delete(post);
	}
	
	@Test
	public void TestSaveGroupPostUser()
	{
		Video dbPost = videoDao.GetById(post.getId());
		assertEquals(post.getUser(), dbPost.getUser());
	}
	
	@Test
	public void TestSaveGroupPostComment()
	{
		Video dbPost = videoDao.GetById(post.getId());
		assertEquals(post.getComments().iterator().next(), dbPost.getComments().iterator().next());
	}
}
