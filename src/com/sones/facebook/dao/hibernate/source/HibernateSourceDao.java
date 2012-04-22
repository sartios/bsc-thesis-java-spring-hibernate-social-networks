package com.sones.facebook.dao.hibernate.source;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.dao.source.ISourceDao;
import com.sones.facebook.model.source.Source;
import com.sones.facebook.model.source.SourceType;

public class HibernateSourceDao extends HibernateGenericDao<Source, String> implements ISourceDao
{
	public HibernateSourceDao()
	{
		super(Source.class);
	}

	@Override
	public Iterable<Source> GetByType(SourceType type) {
		// TODO Auto-generated method stub
		return null;
	}
}
