package com.sones.facebook.usermanager.dao.hibernate;

import org.apache.log4j.Logger;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.usermanager.dao.IApplicationUserPlaceCriteriaDao;
import com.sones.facebook.usermanager.model.ApplicationUserPlaceCriteria;
import com.sones.facebook.usermanager.model.ApplicationUserPlaceCriteriaId;

public class HibernateApplicationUserPlaceCriteriaDao	extends	HibernateGenericDao< ApplicationUserPlaceCriteria, ApplicationUserPlaceCriteriaId >	implements	IApplicationUserPlaceCriteriaDao
{
	private	final	Logger	_LOGGER;
	
	public	HibernateApplicationUserPlaceCriteriaDao()
	{
		super( ApplicationUserPlaceCriteria.class );
		_LOGGER	=	Logger.getLogger( ApplicationUserPlaceCriteria.class );
	}
}
