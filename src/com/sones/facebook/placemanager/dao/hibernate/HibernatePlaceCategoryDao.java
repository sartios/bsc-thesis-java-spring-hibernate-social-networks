package com.sones.facebook.placemanager.dao.hibernate;

import org.apache.log4j.Logger;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.placemanager.dao.IPlaceCategory;
import com.sones.facebook.placemanager.model.PlaceCategory;

public class HibernatePlaceCategoryDao	extends	HibernateGenericDao< PlaceCategory, String >	implements	IPlaceCategory
{
	private	final	Logger	_LOGGER;
	
	public	HibernatePlaceCategoryDao()
	{
		super( PlaceCategory.class );
		_LOGGER	=	Logger.getLogger( HibernatePlaceCategoryDao.class );
	}
}
