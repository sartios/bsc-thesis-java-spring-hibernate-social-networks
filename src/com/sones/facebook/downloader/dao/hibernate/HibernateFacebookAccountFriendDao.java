package com.sones.facebook.downloader.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.downloader.dao.IFacebookAccountFriendDao;
import com.sones.facebook.downloader.model.FacebookAccountFriend;
import com.sones.facebook.downloader.model.FacebookAccountFriendId;
import com.sones.facebook.tokenmanager.model.FacebookAccount;

public class HibernateFacebookAccountFriendDao extends HibernateGenericDao<FacebookAccountFriend, FacebookAccountFriendId> implements IFacebookAccountFriendDao
{
	public HibernateFacebookAccountFriendDao()
	{
		super(FacebookAccountFriend.class);
	}

	@Override
	public Iterable<FacebookAccountFriend> getByOwner(FacebookAccount owner)
	{

		Session session = getHibernateTemplate().getSessionFactory().openSession();
		
		Criteria criteria = session.createCriteria(FacebookAccountFriend.class)
			.add( Restrictions.eq("id.owner", owner))
			.setResultTransformer( Criteria.DISTINCT_ROOT_ENTITY);

		List<FacebookAccountFriend> results = criteria.list();
		session.close();
		
		return results;
	}
}
