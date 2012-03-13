package com.sones.facebook.dao.hibernate.place;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.dao.place.IPlaceDao;
import com.sones.facebook.model.place.Place;

public class HibernatePlaceDao extends HibernateGenericDao<Place, String> implements IPlaceDao
{
	public HibernatePlaceDao()
	{
		super(Place.class);
	}
}
