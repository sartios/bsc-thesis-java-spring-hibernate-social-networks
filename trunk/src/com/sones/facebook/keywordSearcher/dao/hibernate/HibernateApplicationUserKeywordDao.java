package com.sones.facebook.keywordSearcher.dao.hibernate;

import org.apache.log4j.Logger;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.keywordSearcher.dao.IApplicationUserKeywordDao;
import com.sones.facebook.keywordSearcher.model.ApplicationUserKeyword;
import com.sones.facebook.keywordSearcher.model.ApplicationUserKeywordId;

public class HibernateApplicationUserKeywordDao extends HibernateGenericDao<ApplicationUserKeyword, ApplicationUserKeywordId> implements IApplicationUserKeywordDao
{
	private	final	Logger	_LOGGER;
	
	public HibernateApplicationUserKeywordDao()
	{
		super(ApplicationUserKeyword.class);
		_LOGGER = Logger.getLogger(HibernateApplicationUserKeywordDao.class);
	}

}
