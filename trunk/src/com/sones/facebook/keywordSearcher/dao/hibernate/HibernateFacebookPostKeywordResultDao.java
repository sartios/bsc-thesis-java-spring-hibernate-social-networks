package com.sones.facebook.keywordSearcher.dao.hibernate;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.keywordSearcher.dao.IFacebookPostKeywordResultDao;
import com.sones.facebook.keywordSearcher.model.FacebookPostKeywordResult;
import com.sones.userManager.model.ApplicationUser;

public class HibernateFacebookPostKeywordResultDao extends HibernateGenericDao<FacebookPostKeywordResult, String> implements IFacebookPostKeywordResultDao
{
	/**
	 * The Class logger.
	 */
	private final Logger _LOGGER;
	
	/**
	 * Initializes the object.
	 */
	public HibernateFacebookPostKeywordResultDao() 
	{
		super(FacebookPostKeywordResult.class);
		_LOGGER = Logger.getLogger(HibernateFacebookPostKeywordResultDao.class);
	}

	@Override
	public Iterable<FacebookPostKeywordResult> GetByApplicationUser( ApplicationUser appUser )
	{
		if( appUser	==	null )
		{
			_LOGGER.error( "Application user can't be null!" );
			throw	new	IllegalArgumentException( "Application user can't be null!" );
		}
		
		Session	session	=	getHibernateTemplate().getSessionFactory().openSession();
		Criteria	criteria	=	session.createCriteria( FacebookPostKeywordResult.class )
			.add( Restrictions.eq( "user", appUser ) );
		
		return	criteria.list();
	}

}
