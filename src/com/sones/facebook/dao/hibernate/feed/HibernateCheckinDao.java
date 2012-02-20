package com.sones.facebook.dao.hibernate.feed;

import org.apache.log4j.Logger;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.dao.feed.ICheckinDao;
import com.sones.facebook.model.feed.Checkin;

public class HibernateCheckinDao extends HibernateGenericDao<Checkin, String> implements ICheckinDao
{
	private static Logger _LOGGER = Logger.getLogger(HibernateCheckinDao.class);
	
	public HibernateCheckinDao() 
	{
		super(Checkin.class);
	}
}
