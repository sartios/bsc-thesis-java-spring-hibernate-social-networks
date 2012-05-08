package com.sones.usermanager.dao.hibernate;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.usermanager.dao.IApplicationUserCredentialDao;
import com.sones.usermanager.model.ApplicationUserCredential;

public class HibernateApplicationUserCredentialDao extends HibernateGenericDao<ApplicationUserCredential, String> implements IApplicationUserCredentialDao
{
	public HibernateApplicationUserCredentialDao()
	{
		super(ApplicationUserCredential.class);
	}

	@Override
	public ApplicationUserCredential getByCredentials(
			ApplicationUserCredential appUserCredentials) 
	{
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(ApplicationUserCredential.class)
			.add(Restrictions.eq("username", appUserCredentials.getUsername()))
			.add(Restrictions.eq("password", appUserCredentials.getPassword()));
		ApplicationUserCredential result = (ApplicationUserCredential) criteria.uniqueResult();
		session.close();
		return result;
	}
}
