package com.sones.facebook.dao.hibernate.feed;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sones.facebook.dao.feed.IPagePostDao;
import com.sones.facebook.dao.feed.comment.ICommentDao;
import com.sones.facebook.dao.hibernate.HibernateDaoTesterUtil;
import com.sones.facebook.dao.hibernate.feed.comment.HibernateCommentDao;
import com.sones.facebook.dao.hibernate.source.HibernatePageDao;
import com.sones.facebook.dao.hibernate.source.HibernateUserDao;
import com.sones.facebook.dao.source.IPageDao;
import com.sones.facebook.dao.source.IUserDao;
import com.sones.facebook.model.feed.PagePost;

public class HibernatePagePostDaoTester extends HibernateDaoTesterUtil
{
	private IUserDao userDao;
	private ICommentDao commentDao;
	private IPagePostDao pagePostDao;
	private PagePost post;
	private IPageDao pageDao;
	
	public HibernatePagePostDaoTester()
	{
		super();
	}
	
	@Before
	public void SetUp()
	{
		userDao = (HibernateUserDao) getDAOContext().getBean("userDao");
		commentDao = (HibernateCommentDao) getDAOContext().getBean("commentDao");
		pagePostDao = (HibernatePagePostDao) getDAOContext().getBean("pagePostDao");
		pageDao = (HibernatePageDao) getDAOContext().getBean("pageDao");
		post = (PagePost) getModelContext().getBean("pagePost");
		
		userDao.Save(post.getUser());
		commentDao.Save(post.getComments().iterator().next());
		pageDao.Save(post.getPage());
		pagePostDao.Save(post);
	}

	@After
	public void TearDown()
	{
		commentDao.Delete(post.getComments().iterator().next());
		userDao.Delete(post.getUser());
		pagePostDao.Delete(post);
		pageDao.Delete(post.getPage());
	}
	
	@Test
	public void TestSavePagePostUser()
	{
		PagePost dbPost = pagePostDao.GetById(post.getId());
		assertEquals(post.getUser(), dbPost.getUser());
	}
	
	@Test
	public void TestSavePagePostComment()
	{
		PagePost dbPost = pagePostDao.GetById(post.getId());
		assertEquals(post.getComments().iterator().next(), dbPost.getComments().iterator().next());
	}
	
	@Test
	public void TestSavePagePostPage()
	{
		PagePost dbPost = pagePostDao.GetById(post.getId());
		assertEquals(post.getPage(), dbPost.getPage());
	}
}
