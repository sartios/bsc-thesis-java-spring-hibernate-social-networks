package com.sones.facebook.keywordSearcher.dao.hibernate;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.keywordSearcher.dao.IKeywordSearchOptionDao;
import com.sones.facebook.keywordSearcher.model.KeywordSearchOption;
import com.sones.usermanager.model.ApplicationUser;

public class HibernateKeywordSearchOptionDao extends HibernateGenericDao<KeywordSearchOption, String> implements IKeywordSearchOptionDao
{
	private final Logger _LOGGER;
	
	public HibernateKeywordSearchOptionDao()
	{
		super(KeywordSearchOption.class);
		_LOGGER = Logger.getLogger(KeywordSearchOption.class);
	}

	@Override
	public KeywordSearchOption getByApplicationUser(ApplicationUser appUser) 
	{
		if(appUser == null)
		{
			_LOGGER.error("Application user can't be null!");
			throw new IllegalArgumentException("Application user can't be null!");
		}
		
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(KeywordSearchOption.class)
			.add( Restrictions.eq("appUser", appUser) );
		KeywordSearchOption option = (KeywordSearchOption) criteria.uniqueResult();
		session.close();
		return option;
	}
}
