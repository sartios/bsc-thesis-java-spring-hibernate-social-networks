package com.sones.facebook.keywordSearcher.dao.hibernate;

import org.apache.log4j.Logger;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.keywordSearcher.dao.IKeywordSearchDao;
import com.sones.facebook.keywordSearcher.model.KeywordSearch;

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

}
