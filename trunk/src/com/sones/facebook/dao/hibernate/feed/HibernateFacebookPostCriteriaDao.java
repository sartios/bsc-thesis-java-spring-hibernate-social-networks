package com.sones.facebook.dao.hibernate.feed;

import org.apache.log4j.Logger;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.dao.feed.IFacebookPostCriteriaDao;
import com.sones.facebook.model.feed.FacebookPostCriteria;
import com.sones.facebook.model.feed.FacebookPostCriteriaId;

public class HibernateFacebookPostCriteriaDao	extends	HibernateGenericDao< FacebookPostCriteria, FacebookPostCriteriaId >	implements	IFacebookPostCriteriaDao
{
	private	final	Logger	_LOGGER;
	
	public	HibernateFacebookPostCriteriaDao()
	{
		super( FacebookPostCriteria.class );
		_LOGGER	=	Logger.getLogger( HibernateFacebookPostCriteriaDao.class );
	}
}
