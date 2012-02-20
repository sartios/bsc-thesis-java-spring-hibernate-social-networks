package com.sones.facebook.dao.hibernate.feed;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.facebook.dao.feed.ICheckinDao;
import com.sones.facebook.dao.feed.comment.ICommentDao;
import com.sones.facebook.dao.hibernate.feed.comment.HibernateCommentDao;
import com.sones.facebook.dao.hibernate.source.HibernateUserDao;
import com.sones.facebook.dao.place.IPlaceDao;
import com.sones.facebook.dao.source.IUserDao;
import com.sones.facebook.model.feed.Checkin;
import com.sones.facebook.model.feed.comment.Comment;
import com.sones.facebook.model.place.Place;
import com.sones.facebook.model.source.User;

public class HibernateCheckinDaoTester 
{
	private ApplicationContext context;
	private ICheckinDao checkinDao;
	private IUserDao userDao;
	public ICommentDao commectDao;
	private IPlaceDao placeDao;
	private Checkin checkin;
	private Place place;
	private User user;
	private Comment comment;

	public HibernateCheckinDaoTester()
	{
		context =
		    new ClassPathXmlApplicationContext("spring-facebook-dao.xml");
	}
	
	@Before
	public void SetUp()
	{
		checkinDao = (HibernateCheckinDao)context.getBean("checkinDao");
		userDao = (HibernateUserDao) context.getBean("userDao");
		commectDao = (HibernateCommentDao) context.getBean("commentDao");
		placeDao = (IPlaceDao) context.getBean("placeDao");
		
		checkin = new Checkin();
		
		checkin.setId("1");
		checkin.SetMessage("message");
		checkin.setCreatedDate(Calendar.getInstance().getTime());
		
		// Set Checkin's place.
		place = new Place("Sarti","10000000","100000");
		place.SetId("1");
		checkin.SetPlace(place);
		
		// Set Checkin's user.
		user = new User("Sartios");
		user.setId("1");
		checkin.setUser(user);

		// Set Checkin's comments.
		Set<Comment> comments = new HashSet<Comment>();
		comment = new Comment();
		comment.setId("1");
		comment.setMessage("message");
		comment.setCreatedDate(Calendar.getInstance().getTime());
		comment.setUser(user);
		comments.add(comment);
		checkin.setComments(comments);
		
		userDao.Save(user);
		placeDao.Save(place);
		checkinDao.Save(checkin);
	}
	
	@After
	public void TearDown()
	{
		checkinDao.Delete(checkin);
		commectDao.Delete(comment);
		userDao.Delete(user);
		placeDao.Delete(place);
	}
	
	@Test
	public void TestSaveMessage()
	{		
		Checkin dbCheckin = checkinDao.GetById(checkin.getId());
		assertEquals(checkin.GetMessage(), dbCheckin.GetMessage());
	}
	
	@Test
	public void TestSaveComment()
	{		
		Checkin dbCheckin = checkinDao.GetById(checkin.getId());
		assertEquals(checkin.getComments().iterator().next(), dbCheckin.getComments().iterator().next());
	}
	
	@Test
	public void TestSavePlace()
	{		
		Checkin dbCheckin = checkinDao.GetById(checkin.getId());
		assertEquals(checkin.GetPlace(), dbCheckin.GetPlace());
	}
	
	@Test
	public void TestSaveUser()
	{		
		Checkin dbCheckin = checkinDao.GetById(checkin.getId());
		assertEquals(checkin.getUser(), dbCheckin.getUser());
		
	}
}
