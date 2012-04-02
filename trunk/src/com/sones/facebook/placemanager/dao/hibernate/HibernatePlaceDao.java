package com.sones.facebook.placemanager.dao.hibernate;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.placemanager.dao.IPlaceDao;
import com.sones.facebook.placemanager.model.Place;

public class HibernatePlaceDao extends HibernateGenericDao<Place, String> implements IPlaceDao
{
	public HibernatePlaceDao()
	{
		super(Place.class);
	}
}
