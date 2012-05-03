package com.sones.facebook.downloader.dao.hibernate;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.downloader.dao.IFacebookAccountFriendDao;
import com.sones.facebook.downloader.model.FacebookAccountFriend;
import com.sones.facebook.downloader.model.FacebookAccountFriendId;

public class HibernateFacebookAccountFriendDao extends HibernateGenericDao<FacebookAccountFriend, FacebookAccountFriendId> implements IFacebookAccountFriendDao
{
	public HibernateFacebookAccountFriendDao()
	{
		super(FacebookAccountFriend.class);
	}
}
