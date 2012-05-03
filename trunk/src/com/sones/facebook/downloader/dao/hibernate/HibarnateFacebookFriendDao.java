package com.sones.facebook.downloader.dao.hibernate;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.downloader.dao.IFacebookFriendDao;
import com.sones.facebook.downloader.model.FacebookFriend;

public class HibarnateFacebookFriendDao extends HibernateGenericDao<FacebookFriend, String> implements IFacebookFriendDao
{
	public HibarnateFacebookFriendDao()
	{
		super(FacebookFriend.class);
	}
}
