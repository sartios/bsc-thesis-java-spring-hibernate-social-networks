package com.sones.facebook.dao.hibernate.feed;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.dao.feed.ILinkDao;
import com.sones.facebook.model.feed.Link;

public class HibernateLinkDao  extends HibernateGenericDao<Link, String> implements ILinkDao
{
	public HibernateLinkDao()
	{
		super(Link.class);
	}
}
