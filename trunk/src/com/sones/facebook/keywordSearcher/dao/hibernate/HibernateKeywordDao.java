package com.sones.facebook.keywordSearcher.dao.hibernate;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.keywordSearcher.dao.IKeywordDao;
import com.sones.facebook.keywordSearcher.model.Keyword;

public class HibernateKeywordDao extends HibernateGenericDao<Keyword, String> implements IKeywordDao
{

	/**
	 * The Class logger.
	 */
	private	final	Logger	_LOGGER;
	
	/**
	 * Initializes the object.
	 */
	public HibernateKeywordDao()
	{
		super(Keyword.class);
		_LOGGER	=	Logger.getLogger(HibernateKeywordDao.class);
	}

	@Override
	public Keyword getByValue(Keyword keyword) 
	{
		checkNullability( keyword, "Keyword can't be null" );
		
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Criteria criteria = session.createCriteria( Keyword.class )
			.add( Restrictions.eq("value", keyword.getValue()) )
			.setFirstResult(0)
			.setMaxResults(1);
		List<Keyword> results = criteria.list();
		session.close();
		Keyword result = null;
		if( (results != null) && (results.isEmpty() == false) )
		{
			result = results.get(0);
		}
		return result;
	}
	
	private void checkNullability(Object object, String message) throws IllegalArgumentException
	{
		if(object == null)
		{
			_LOGGER.error(message);
			throw new IllegalArgumentException(message);
		}
	}
}
