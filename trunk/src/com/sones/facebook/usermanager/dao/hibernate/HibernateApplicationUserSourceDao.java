package com.sones.facebook.usermanager.dao.hibernate;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.usermanager.dao.IApplicationUserSourceDao;
import com.sones.facebook.usermanager.model.ApplicationUserSource;
import com.sones.facebook.usermanager.model.ApplicationUserSourceId;
import com.sones.usermanager.model.ApplicationUser;

public class HibernateApplicationUserSourceDao extends HibernateGenericDao<ApplicationUserSource, ApplicationUserSourceId> implements IApplicationUserSourceDao
{
	private	final	Logger _LOGGER;

	public HibernateApplicationUserSourceDao()
	{
		super(ApplicationUserSource.class);
		_LOGGER = Logger.getLogger(HibernateApplicationUserSourceDao.class);
	}

	@Override
	public Iterable<ApplicationUserSource> getApplicationUserSourcesByUser( ApplicationUser appUser )
	{
		if( appUser == null )
		{
			_LOGGER.error( "Application user can't be null!" );
			throw	new	IllegalArgumentException( "Application user can't be null" );
		}
		
		Session	session	=	getHibernateTemplate().getSessionFactory().openSession();
		Criteria	criteria	=	session.createCriteria(ApplicationUserSource.class)
										.add(Restrictions.eq("id.appUser", appUser));
		List<ApplicationUserSource> list = criteria.list();
		return list;
	}

}
