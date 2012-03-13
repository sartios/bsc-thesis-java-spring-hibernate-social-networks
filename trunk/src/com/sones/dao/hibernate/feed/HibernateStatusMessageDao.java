package com.sones.facebook.dao.hibernate.feed;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.dao.feed.IStatusMessageDao;
import com.sones.facebook.model.feed.StatusMessage;

public class HibernateStatusMessageDao extends HibernateGenericDao<StatusMessage, String> implements IStatusMessageDao
{
	public HibernateStatusMessageDao()
	{
		super(StatusMessage.class);
	}
}
