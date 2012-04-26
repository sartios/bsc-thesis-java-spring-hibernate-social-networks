package com.sones.facebook.tokenmanager.dao.hibernate;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.tokenmanager.model.FacebookToken;
import com.sones.facebook.tokenmanager.dao.IFacebookTokenDao;
import com.sones.usermanager.model.ApplicationUser;

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

	@Override
	public FacebookToken GetByApplicationUser(ApplicationUser appUser) 
	{
		if( appUser == null )
		{
			_LOGGER.error( "Application user can't be null" );
			throw	new	IllegalArgumentException( "Application user can't be null" );
		}
		if( appUser.getId() == null )
		{
			_LOGGER.error( "Application user id can't be null" );
			throw	new	IllegalArgumentException( "Application user id can't be null" );
		}
		
		Session	session	=	getHibernateTemplate().getSessionFactory().openSession();
		
		Criteria	criteria	=	session.createCriteria( FacebookToken.class )
			.createCriteria( "account" )
			.add( Restrictions.eq("appUser", appUser));
		
		List	results	=	criteria.list();
		FacebookToken	token	=	null;
		if( results.size() > 0 )
		{
			token	=	(FacebookToken) results.get( 0 );
		}
		return	token;
	}
	
}
