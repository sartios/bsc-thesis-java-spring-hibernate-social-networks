package com.sones.facebook.dao.hibernate.feed;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sones.facebook.dao.feed.IGroupPostDao;
import com.sones.facebook.dao.feed.comment.ICommentDao;
import com.sones.facebook.dao.hibernate.HibernateDaoTesterUtil;
import com.sones.facebook.dao.hibernate.feed.comment.HibernateCommentDao;
import com.sones.facebook.dao.hibernate.source.HibernateGroupDao;
import com.sones.facebook.dao.hibernate.source.HibernateUserDao;
import com.sones.facebook.dao.source.IGroupDao;
import com.sones.facebook.dao.source.IUserDao;
import com.sones.facebook.model.feed.GroupPost;

public class HibernateGroupPostDaoTester extends HibernateDaoTesterUtil
{
	private IUserDao userDao;
	private ICommentDao commentDao;
	private IGroupPostDao groupPostDao;
	private GroupPost post;
	private IGroupDao groupDao;
	
	public HibernateGroupPostDaoTester()
	{
		super();
	}
	
	@Before
	public void SetUp()
	{
		userDao = (HibernateUserDao) getDAOContext().getBean("userDao");
		commentDao = (HibernateCommentDao) getDAOContext().getBean("commentDao");
		groupPostDao = (HibernateGroupPostDao) getDAOContext().getBean("groupPostDao");
		groupDao = (HibernateGroupDao) getDAOContext().getBean("groupDao");
		post = (GroupPost) getModelContext().getBean("groupPost");
		
		userDao.Save(post.getUser());
		commentDao.Save(post.getComments().iterator().next());
		groupDao.Save(post.getGroup());
		groupPostDao.Save(post);
	}

	@After
	public void TearDown()
	{
		commentDao.Delete(post.getComments().iterator().next());
		userDao.Delete(post.getUser());
		groupPostDao.Delete(post);
		groupDao.Delete(post.getGroup());
	}
	
	@Test
	public void TestSaveGroupPostUser()
	{
		GroupPost dbPost = groupPostDao.GetById(post.getId());
		assertEquals(post.getUser(), dbPost.getUser());
	}
	
	@Test
	public void TestSaveGroupPostComment()
	{
		GroupPost dbPost = groupPostDao.GetById(post.getId());
		assertEquals(post.getComments().iterator().next(), dbPost.getComments().iterator().next());
	}
	
	@Test
	public void TestSaveGroupPostGroup()
	{
		GroupPost dbPost = groupPostDao.GetById(post.getId());
		assertEquals(post.getGroup(), dbPost.getGroup());
	}
}
