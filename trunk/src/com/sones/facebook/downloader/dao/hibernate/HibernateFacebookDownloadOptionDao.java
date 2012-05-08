package com.sones.facebook.downloader.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.downloader.dao.IFacebookDownloadOptionDao;
import com.sones.facebook.downloader.model.FacebookDownloadOption;
import com.sones.facebook.tokenmanager.model.FacebookAccount;

public class HibernateFacebookDownloadOptionDao extends HibernateGenericDao<FacebookDownloadOption, String> implements IFacebookDownloadOptionDao
{
	public HibernateFacebookDownloadOptionDao()
	{
		super(FacebookDownloadOption.class);
	}

	@Override
	public FacebookDownloadOption getByAccount(FacebookAccount account)
	{
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(FacebookDownloadOption.class)
			.add( Restrictions.eq("account", account));
		List<FacebookDownloadOption> results = criteria.list();
		session.close();
		FacebookDownloadOption option = null;
		if(results.isEmpty() == false)
		{
			option = results.get(0);
		}
		return option;
	}
}
