package com.sones.facebook.dao.hibernate.feed;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sones.facebook.dao.feed.ILinkDao;
import com.sones.facebook.dao.feed.IPhotoDao;
import com.sones.facebook.dao.feed.comment.ICommentDao;
import com.sones.facebook.dao.hibernate.HibernateDaoTesterUtil;
import com.sones.facebook.dao.hibernate.feed.comment.HibernateCommentDao;
import com.sones.facebook.dao.hibernate.source.HibernateUserDao;
import com.sones.facebook.dao.source.IUserDao;
import com.sones.facebook.model.feed.Link;
import com.sones.facebook.model.feed.Photo;

public class HibernatePhotoDaoTester extends HibernateDaoTesterUtil
{
	private IUserDao userDao;
	private ICommentDao commentDao;
	private IPhotoDao photoDao;
	private Photo post;
	
	public HibernatePhotoDaoTester()
	{
		super();
	}
	
	@Before
	public void SetUp()
	{
		userDao = (HibernateUserDao) getDAOContext().getBean("userDao");
		commentDao = (HibernateCommentDao) getDAOContext().getBean("commentDao");
		photoDao = (HibernatePhotoDao) getDAOContext().getBean("photoDao");
		post = (Photo) getModelContext().getBean("photo");
		
		userDao.Save(post.getUser());
		commentDao.Save(post.getComments().iterator().next());
		photoDao.Save(post);
	}

	@After
	public void TearDown()
	{
		commentDao.Delete(post.getComments().iterator().next());
		userDao.Delete(post.getUser());
		photoDao.Delete(post);
	}
	
	@Test
	public void TestSaveGroupPostUser()
	{
		Photo dbPost = photoDao.GetById(post.getId());
		assertEquals(post.getUser(), dbPost.getUser());
	}
	
	@Test
	public void TestSaveGroupPostComment()
	{
		Photo dbPost = photoDao.GetById(post.getId());
		assertEquals(post.getComments().iterator().next(), dbPost.getComments().iterator().next());
	}
}
