package com.sones.facebook.dao.hibernate.source;

import org.apache.log4j.Logger;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.dao.source.IApplicationUserSourceDao;
import com.sones.facebook.model.source.ApplicationUserSource;
import com.sones.facebook.model.source.ApplicationUserSourceId;

public class HibernateApplicationUserSourceDao extends HibernateGenericDao<ApplicationUserSource, ApplicationUserSourceId> implements IApplicationUserSourceDao
{
	private	final	Logger _LOGGER;

	public HibernateApplicationUserSourceDao()
	{
		super(ApplicationUserSource.class);
		_LOGGER = Logger.getLogger(HibernateApplicationUserSourceDao.class);
	}

}
