package com.sones.userManager.dao.hibernate;

import org.apache.log4j.Logger;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.userManager.dao.IApplicationUserDao;
import com.sones.userManager.model.ApplicationUser;

public class HibernateApplicationUserDao extends HibernateGenericDao<ApplicationUser, String> implements IApplicationUserDao
{

	private	final	Logger	_LOGGER;
	
	public HibernateApplicationUserDao()
	{
		super(ApplicationUser.class);
		_LOGGER	=	Logger.getLogger(HibernateApplicationUserDao.class);
	}

}
