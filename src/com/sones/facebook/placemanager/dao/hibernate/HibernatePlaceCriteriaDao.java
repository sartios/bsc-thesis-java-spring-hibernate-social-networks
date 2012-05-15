package com.sones.facebook.placemanager.dao.hibernate;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.placemanager.dao.IPlaceCriteriaDao;
import com.sones.facebook.placemanager.model.PlaceCriteria;
import com.sones.facebook.placemanager.model.PlaceCriteriaId;
import com.sones.facebook.publicsource.model.Criteria;

public class HibernatePlaceCriteriaDao	extends	HibernateGenericDao< PlaceCriteria, PlaceCriteriaId >	implements	IPlaceCriteriaDao
{
	private	final	Logger	_LOGGER;
	
	public	HibernatePlaceCriteriaDao()
	{
		super( PlaceCriteria.class );
		_LOGGER	=	Logger.getLogger( HibernatePlaceCriteriaDao.class );
	}

	@Override
	public Iterable<PlaceCriteria> GetByCriteria(Criteria criteria) 
	{
		if(criteria == null)
		{
			_LOGGER.error("Criteria can't be null!");
			throw new IllegalArgumentException("Criteria can't be null!");
		}
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		org.hibernate.Criteria query = session.createCriteria(PlaceCriteria.class)
			.add( Restrictions.eq("id.criteria", criteria) );
		List<PlaceCriteria> results = query.list();
		session.close();
		return results;
	}
}
