package com.sones.facebook.dao.hibernate.source;

import org.hibernate.criterion.Restrictions;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.dao.source.ISourceTypeDao;
import com.sones.facebook.model.source.SourceType;

public class HibernateSourceTypeDao extends HibernateGenericDao<SourceType, String> implements ISourceTypeDao
{
	public HibernateSourceTypeDao()
	{
		super(SourceType.class);
	}

	@Override
	public SourceType GetByType(SourceType sourceType) 
	{
		if( sourceType == null )
		{
			throw new IllegalArgumentException("Source type can't be null!");
		}
		if( sourceType.getType() == null )
		{
			throw new IllegalArgumentException("Source type value can't be null!");
		}
		if( sourceType.getType().isEmpty() )
		{
			throw new IllegalArgumentException("Source type value can't be empty!");
		}
		SourceType dbSourceType;
		try
		{
			dbSourceType = (SourceType) getHibernateTemplate().getSessionFactory().openSession().createCriteria(SourceType.class)
			.add(
					Restrictions
					.like("type",sourceType.getType()
				))
			.list()
			.get(0);
		}
		catch(IndexOutOfBoundsException ex)
		{
			dbSourceType = null;
		}
		return dbSourceType;
	}
}
