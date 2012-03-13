package com.sones.facebook.dao.hibernate.feed;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.dao.feed.IEventPostDao;
import com.sones.facebook.model.feed.EventPost;

public class HibernateEventPostDao  extends HibernateGenericDao<EventPost, String> implements IEventPostDao
{
	public HibernateEventPostDao()
	{
		super(EventPost.class);
	}
}
