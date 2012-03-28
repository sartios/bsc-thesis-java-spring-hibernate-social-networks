package com.sones.facebook.tokenmanager.model.dao.hibernate;

import org.apache.log4j.Logger;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.tokenmanager.model.FacebookToken;
import com.sones.facebook.tokenmanager.model.dao.IFacebookTokenDao;

public class HibernateFacebookTokenDao	extends	HibernateGenericDao< FacebookToken, String >	implements	IFacebookTokenDao
{
	/**
	 * The class logger.
	 */
	private	final	Logger	_LOGGER;
	
	/**
	 * Initializes the object.
	 */
	public	HibernateFacebookTokenDao()
	{
		super( FacebookToken.class );
		_LOGGER	=	Logger.getLogger( HibernateFacebookTokenDao.class );
	}
	
}
