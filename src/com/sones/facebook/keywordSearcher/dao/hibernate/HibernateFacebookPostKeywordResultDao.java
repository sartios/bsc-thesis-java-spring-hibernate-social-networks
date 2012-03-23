package com.sones.facebook.keywordSearcher.dao.hibernate;

import org.apache.log4j.Logger;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.keywordSearcher.dao.IFacebookPostKeywordResultDao;
import com.sones.facebook.keywordSearcher.model.FacebookPostKeywordResult;

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

}
