package com.sones.facebook.dao.hibernate.place;

import org.apache.log4j.Logger;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.dao.place.IPlaceCategory;
import com.sones.facebook.model.place.PlaceCategory;

public class HibernatePlaceCategoryDao	extends	HibernateGenericDao< PlaceCategory, String >	implements	IPlaceCategory
{
	private	final	Logger	_LOGGER;
	
	public	HibernatePlaceCategoryDao()
	{
		super( PlaceCategory.class );
		_LOGGER	=	Logger.getLogger( HibernatePlaceCategoryDao.class );
	}
}
