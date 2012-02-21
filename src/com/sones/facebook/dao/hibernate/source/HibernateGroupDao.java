package com.sones.facebook.dao.hibernate.source;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.dao.source.IGroupDao;
import com.sones.facebook.model.source.Group;

public class HibernateGroupDao  extends HibernateGenericDao<Group, String> implements IGroupDao
{
	public HibernateGroupDao()
	{
		super(Group.class);
	}
}
