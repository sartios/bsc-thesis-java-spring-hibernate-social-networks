package com.sones.usermanager.dao.hibernate;

import org.apache.log4j.Logger;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.usermanager.dao.IApplicationUserDao;
import com.sones.usermanager.model.ApplicationUser;


public class HibernateApplicationUserDao extends HibernateGenericDao<ApplicationUser, String> implements IApplicationUserDao
{

	private	final	Logger	_LOGGER;
	
	public HibernateApplicationUserDao()
	{
		super(ApplicationUser.class);
		_LOGGER	=	Logger.getLogger(HibernateApplicationUserDao.class);
	}

}
