package com.sones.facebook.downloader.dao.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.downloader.dao.IFacebookPostDownloadDao;
import com.sones.facebook.downloader.model.FacebookDownload;
import com.sones.facebook.downloader.model.FacebookPostDownload;
import com.sones.facebook.downloader.model.FacebookPostDownloadId;
import com.sones.usermanager.model.ApplicationUser;

public class HibernateFacebookPostDownloadDao	extends	HibernateGenericDao<FacebookPostDownload, FacebookPostDownloadId>	implements	IFacebookPostDownloadDao
{
	/***
	 * The class logger.
	 */
	private	final	Logger	_LOGGER;
	
	/**
	 * Initializes the object.
	 */
	public	HibernateFacebookPostDownloadDao()
	{
		super(FacebookPostDownload.class);
		_LOGGER	=	Logger.getLogger( HibernateFacebookPostDownloadDao.class );
	}

	@Override
	public Iterable<FacebookPostDownload> getFacebookPostAfterDateByAppUser( Date date, ApplicationUser appUser ) 
	{
		if( date == null )
		{
			_LOGGER.error( "Date can't be null!" );
			throw	new	IllegalArgumentException( "Date can't be null!" );
		}
		if( appUser == null )
		{
			_LOGGER.error( "Application user can't be null!" );
			throw	new	IllegalArgumentException( "Application user can't be null!" );
		}
		
		Session	session	=	getHibernateTemplate().getSessionFactory().openSession();
		Criteria	downloadCriteria	=	session.createCriteria(FacebookDownload.class)
			.add( Restrictions.eq( "appUser", appUser ) )
			.add( Restrictions.ge("date", date));
		List<FacebookDownload>	downloads	=	downloadCriteria.list();
		if( downloads == null )
		{
			_LOGGER.error( "There is not download which agrees with the criteria" );
			return	null;
		}
		if( downloads.size() == 0 )
		{
			_LOGGER.error( "There is not download which agrees with the criteria" );
			return	null;
		}
		Set<FacebookPostDownload>	results	=	new	HashSet<FacebookPostDownload>();
		for( FacebookDownload download : downloads )
		{
			Criteria	criteria	=	session.createCriteria( FacebookPostDownload.class )
				.add( Restrictions.eq( "id.download", download ) );
			List<FacebookPostDownload>	list	=	criteria.list();
			if( list != null )
			{
				for( FacebookPostDownload result : list )
				{
					if( result != null )
					{
						results.add( result );
					}
				}
			}
		}
		/*Criteria	criteria	=	session.createCriteria( FacebookPostDownload.class );
			//.createCriteria(FacebookDownload.class)
			//.add( Restrictions.eq("appUser", appUser));
			//.add( Restrictions.ge( "date", date ) );
			//.createCriteria("id.download")
			//.createCriteria("appUser")
			//.add( Restrictions.eq( "id", "1" ) );
		Criteria	downloadCriteria	=	criteria.createCriteria("id.download");
		//downloadCriteria.add( Restrictions.eq("appUser", appUser));
		Criteria	userCriteria	=	downloadCriteria.createCriteria("appUser");
		userCriteria.add(Restrictions.eq("id", appUser.getId()));*/
		
		return results;
	}
}
