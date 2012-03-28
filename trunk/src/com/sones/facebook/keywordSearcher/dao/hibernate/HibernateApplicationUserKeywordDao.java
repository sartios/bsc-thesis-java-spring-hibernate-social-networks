package com.sones.facebook.keywordSearcher.dao.hibernate;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.keywordSearcher.dao.IApplicationUserKeywordDao;
import com.sones.facebook.keywordSearcher.model.ApplicationUserKeyword;
import com.sones.facebook.keywordSearcher.model.ApplicationUserKeywordId;
import com.sones.userManager.model.ApplicationUser;

public class HibernateApplicationUserKeywordDao extends HibernateGenericDao<ApplicationUserKeyword, ApplicationUserKeywordId> implements IApplicationUserKeywordDao
{
	private	final	Logger	_LOGGER;
	
	public HibernateApplicationUserKeywordDao()
	{
		super(ApplicationUserKeyword.class);
		_LOGGER = Logger.getLogger(HibernateApplicationUserKeywordDao.class);
	}

	@Override
	public Iterable<ApplicationUserKeyword> getByApplicationUser( ApplicationUser appUser )
	{
		if( appUser == null )
		{
			_LOGGER.error( "Application user can't be null!" );
			throw	new	IllegalArgumentException( "Application user can't be null!" );
		}
		
		Session	session	=	getHibernateTemplate().getSessionFactory().openSession();
		Criteria	criteria	=	session.createCriteria( ApplicationUserKeyword.class )
			.add( Restrictions.eq( "id.appUser" , appUser));
		List< ApplicationUserKeyword >	results	=	criteria.list();
		session.close();
		return	results;
	}

}
