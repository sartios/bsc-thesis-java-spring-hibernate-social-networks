package com.sones.facebook.dao.hibernate.place;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.dao.place.IVenueDao;
import com.sones.facebook.model.place.Venue;

public class HibernateVenueDao extends HibernateGenericDao<Venue, String> implements IVenueDao
{
	public HibernateVenueDao()
	{
		super(Venue.class);
	}
}
