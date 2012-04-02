package com.sones.facebook.placemanager.dao.hibernate;

import org.apache.log4j.Logger;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.placemanager.dao.IPlaceCriteriaDao;
import com.sones.facebook.placemanager.model.PlaceCriteria;
import com.sones.facebook.placemanager.model.PlaceCriteriaId;

public class HibernatePlaceCriteriaDao	extends	HibernateGenericDao< PlaceCriteria, PlaceCriteriaId >	implements	IPlaceCriteriaDao
{
	private	final	Logger	_LOGGER;
	
	public	HibernatePlaceCriteriaDao()
	{
		super( PlaceCriteria.class );
		_LOGGER	=	Logger.getLogger( HibernatePlaceCriteriaDao.class );
	}
}
