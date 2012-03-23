package com.sones.facebook.keywordSearcher.dao.hibernate;

import org.apache.log4j.Logger;

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

}
