package com.sones.facebook.keywordSearcher.dao.hibernate;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.keywordSearcher.dao.IKeywordSearchDao;
import com.sones.facebook.keywordSearcher.model.KeywordSearch;
import com.sones.usermanager.model.ApplicationUser;

public class HibernateKeywordSearchDao extends HibernateGenericDao<KeywordSearch, String> implements IKeywordSearchDao
{
	/**
	 * The Class logger.
	 */
	private	final	Logger	_LOGGER;

	/**
	 * Initializes the object.
	 */
	public HibernateKeywordSearchDao() 
	{
		super(KeywordSearch.class);
		_LOGGER	=	Logger.getLogger(HibernateKeywordSearchDao.class);
	}

	@Override
	public KeywordSearch getLastKeywordSearchByAppUser(ApplicationUser appUser) 
	{
		if( appUser == null )
		{
			_LOGGER.error("Application user can't be null!");
			throw	new	IllegalArgumentException("Application user can't be null!");
		}
		
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(KeywordSearch.class)
			.addOrder(Order.desc("date"))
			.createCriteria("user")
			.add(Restrictions.eq("id", appUser.getId()))
			.setFirstResult(0);
		
		List	results	=	criteria.list();
		int	resultsSize	=	results.size();
		KeywordSearch	search	=	null;
		if( resultsSize <= 0 )
		{
			_LOGGER.info("There is not a keyword search for this application user.");
		}
		else	if	( resultsSize > 0 )
		{
			search	=	(KeywordSearch) results.get( 0 );
		}
		
		return search;
	}
}
