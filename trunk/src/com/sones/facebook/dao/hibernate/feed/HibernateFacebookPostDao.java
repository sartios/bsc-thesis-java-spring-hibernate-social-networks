package com.sones.facebook.dao.hibernate.feed;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.dao.feed.IFacebookPostDao;
import com.sones.facebook.model.feed.FacebookPost;

public class HibernateFacebookPostDao  extends HibernateGenericDao<FacebookPost, String> implements IFacebookPostDao
{
	public HibernateFacebookPostDao()
	{
		super(FacebookPost.class);
	}
}
