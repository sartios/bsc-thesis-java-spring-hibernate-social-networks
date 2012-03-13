package com.sones.facebook.dao.hibernate.source;


import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.dao.source.IUserDao;
import com.sones.facebook.model.source.User;

public class HibernateUserDao extends HibernateGenericDao<User, String> implements IUserDao
{
	public HibernateUserDao()
	{
		super(User.class);
	}
}
