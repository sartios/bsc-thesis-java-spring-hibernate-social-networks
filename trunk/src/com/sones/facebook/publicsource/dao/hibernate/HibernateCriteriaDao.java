package com.sones.facebook.publicsource.dao.hibernate;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.publicsource.dao.ICriteriaDao;
import com.sones.facebook.publicsource.model.Criteria;

public class HibernateCriteriaDao	extends	HibernateGenericDao< Criteria, String>	implements	ICriteriaDao
{
	private	final	Logger	_LOGGER;
	
	public	HibernateCriteriaDao()
	{
		super( Criteria.class );
		_LOGGER	=	Logger.getLogger( HibernateCriteriaDao.class );
	}

	@Override
	public Criteria GetByValue(String value) 
	{
		CheckNullability( value , "Criteria value can't be null" );
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		org.hibernate.Criteria criteria = session.createCriteria( Criteria.class )
			.add( Restrictions.eq("value", value))
			.setFirstResult(0)
			.setMaxResults(1);
		List results = criteria.list();
		Criteria result = null;
		if( results.isEmpty() == false )
		{
			result = (Criteria) results.get(0);
		}
		session.close();
		return result;
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
