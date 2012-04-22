package com.sones.facebook.downloader.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.downloader.dao.IFacebookDownloadDao;
import com.sones.facebook.downloader.model.FacebookDownload;
import com.sones.usermanager.model.ApplicationUser;

public class HibernateFacebookDownloadDao	extends	HibernateGenericDao<FacebookDownload, String>	implements	IFacebookDownloadDao
{
	private	final	Logger	_LOGGER;
	
	public	HibernateFacebookDownloadDao()
	{
		super(FacebookDownload.class);
		_LOGGER	=	Logger.getLogger( HibernateFacebookDownloadDao.class );
	}

	@Override
	public FacebookDownload GetLastAppUserDownload(ApplicationUser appUser)
	{
		if( appUser == null )
		{
			_LOGGER.error( "Application user can't be null!" );
			throw	new	IllegalArgumentException( "Application user can't be null!" );
		}
		if( appUser.getId() == null )
		{
			_LOGGER.error( "Application user id can't be null!" );
			throw	new	IllegalArgumentException( "Application user id can't be null!" );
		}
		
		Session	session	=	getHibernateTemplate().getSessionFactory().openSession();
		
		Criteria	criteria	=	session.createCriteria( FacebookDownload.class )
			.add( Restrictions.eq( "appUser", appUser ) )
			.addOrder( Order.desc( "date" ))
			.setFirstResult( 0 )
			.setMaxResults( 1 );
		List	results	=	criteria.list();
		FacebookDownload	download	=	null;
		if( results.size() > 0 )
		{
			download	=	(FacebookDownload) results.get( 0 );
		}
		session.close();
		return	download;
	}

	@Override
	public Iterable<FacebookDownload> GetByAppUserAfterDate(ApplicationUser appUser, Date date) 
	{
		if(appUser == null)
		{
			_LOGGER.error("Application user can't be null.");
			throw new IllegalArgumentException("Application user can't be null.");
		}
		if(date == null)
		{
			_LOGGER.error("Date can't be null.");
			throw new IllegalArgumentException("Date can't be null.");
		}
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(FacebookDownload.class)
			.add( Restrictions.eq("appUser", appUser) )
			.add( Restrictions.ge("date", date) );
		List results = criteria.list();
		session.close();
		return results;
	}
}
