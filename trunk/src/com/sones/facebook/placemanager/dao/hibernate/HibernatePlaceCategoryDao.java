package com.sones.facebook.placemanager.dao.hibernate;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.placemanager.dao.IPlaceCategoryDao;
import com.sones.facebook.placemanager.model.PlaceCategory;

public class HibernatePlaceCategoryDao	extends	HibernateGenericDao< PlaceCategory, String >	implements	IPlaceCategoryDao
{
	private	final	Logger	_LOGGER;
	
	public	HibernatePlaceCategoryDao()
	{
		super( PlaceCategory.class );
		_LOGGER	=	Logger.getLogger( HibernatePlaceCategoryDao.class );
	}

	@Override
	public PlaceCategory GetByDescription(String description) 
	{
		CheckNullability( description , "Category description can't be null.");
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Criteria criteria = session.createCriteria( PlaceCategory.class )
			.add( Restrictions.eq( "description", description) )
			.setFirstResult( 0 )
			.setMaxResults( 1 );
		List results = criteria.list();
		PlaceCategory placeCategory = null;
		if( results.isEmpty() == false )
		{
			placeCategory = (PlaceCategory) results.get( 0 );
		}
		session.close();
		return placeCategory;
	}
	
	private void CheckNullability( Object object, String message )
	{
		if( object == null )
		{
			_LOGGER.error( message );
			throw new IllegalArgumentException( message );
		}
	}
}
