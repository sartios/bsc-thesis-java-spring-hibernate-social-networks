package com.sones.facebook.dao.hibernate.source;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.dao.source.IEventDao;
import com.sones.facebook.model.source.Event;

public class HibernateEventDao  extends HibernateGenericDao<Event, String> implements IEventDao
{
	public HibernateEventDao()
	{
		super(Event.class);
	}
}
