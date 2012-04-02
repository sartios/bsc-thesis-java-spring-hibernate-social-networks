package com.sones.facebook.publicsource.dao.hibernate;

import org.apache.log4j.Logger;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.publicsource.dao.ICriteriaDao;
import com.sones.facebook.publicsource.model.Criteria;

public class HibernateCriteriaDao	extends	HibernateGenericDao< Criteria, String>	implements	ICriteriaDao
{
	private	final	Logger	_LOGGER;
	
	public	HibernateCriteriaDao()
	{
		super( Criteria.class );
		_LOGGER	=	Logger.getLogger( HibernateCriteriaDao.class );
	}
}
