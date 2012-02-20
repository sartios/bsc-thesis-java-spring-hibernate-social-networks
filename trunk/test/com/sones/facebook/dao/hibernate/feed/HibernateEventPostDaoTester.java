package com.sones.facebook.dao.hibernate.feed;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.facebook.dao.feed.IEventPostDao;
import com.sones.facebook.dao.feed.comment.ICommentDao;
import com.sones.facebook.dao.hibernate.HibernateDaoTesterUtil;
import com.sones.facebook.dao.hibernate.feed.comment.HibernateCommentDao;
import com.sones.facebook.dao.hibernate.source.HibernateEventDao;
import com.sones.facebook.dao.hibernate.source.HibernateUserDao;
import com.sones.facebook.dao.source.IEventDao;
import com.sones.facebook.dao.source.IUserDao;
import com.sones.facebook.model.feed.EventPost;

public class HibernateEventPostDaoTester extends HibernateDaoTesterUtil
{
	private EventPost eventPost;
	private IEventPostDao eventPostDao;
	private IUserDao userDao;
	private ICommentDao commentDao;
	private IEventDao eventDao;
	
	public HibernateEventPostDaoTester()
	{
		super();
	}
	
	@Before
	public void SetUp()
	{
		eventPost = (EventPost) getModelContext().getBean("eventPost");
		eventPostDao = (HibernateEventPostDao) getDAOContext().getBean("eventPostDao");
		userDao = (HibernateUserDao) getDAOContext().getBean("userDao");
		commentDao = (HibernateCommentDao) getDAOContext().getBean("commentDao");
		eventDao = (HibernateEventDao) getDAOContext().getBean("eventDao");
		
		eventDao.Save(eventPost.getEvent());
		userDao.Save(eventPost.getUser());
		commentDao.Save(eventPost.getComments().iterator().next());
		eventPostDao.Save(eventPost);
	}
	
	@After
	public void TearDown()
	{
		commentDao.Delete(eventPost.getComments().iterator().next());
		eventPostDao.Delete(eventPost);
		userDao.Delete(eventPost.getUser());
		eventDao.Delete(eventPost.getEvent());
	}
	
	@Test
	public void TestSaveEventPostUser()
	{
		EventPost dbPost = eventPostDao.GetById(eventPost.getId());
		assertEquals(eventPost.getUser(), dbPost.getUser());
	}
	
	@Test
	public void TestSaveEventPostComment()
	{
		EventPost dbPost = eventPostDao.GetById(eventPost.getId());
		assertEquals(eventPost.getComments().iterator().next(), dbPost.getComments().iterator().next());
	}
	
	@Test
	public void TestSaveEventPostEvent()
	{
		EventPost dbPost = eventPostDao.GetById(eventPost.getId());
		assertEquals(eventPost.getEvent(), dbPost.getEvent());
	}
}
