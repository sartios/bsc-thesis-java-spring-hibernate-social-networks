package com.sones.facebook.placemanager.dao.hibernate;

import org.apache.log4j.Logger;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.placemanager.dao.IPlaceFacebookPostDao;
import com.sones.facebook.placemanager.model.PlaceFacebookPost;
import com.sones.facebook.placemanager.model.PlaceFacebookPostId;

public class HibernatePlaceFacebookPostDao	extends	HibernateGenericDao< PlaceFacebookPost, PlaceFacebookPostId >	implements	IPlaceFacebookPostDao
{
	private	final	Logger	_LOGGER;
	
	public	HibernatePlaceFacebookPostDao()
	{
		super( PlaceFacebookPost.class );
		_LOGGER	=	Logger.getLogger( HibernatePlaceFacebookPostDao.class );
	}
}
