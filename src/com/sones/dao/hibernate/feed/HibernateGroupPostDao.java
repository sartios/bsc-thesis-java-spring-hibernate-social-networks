package com.sones.facebook.dao.hibernate.feed;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.dao.feed.IGroupPostDao;
import com.sones.facebook.model.feed.GroupPost;

public class HibernateGroupPostDao  extends HibernateGenericDao<GroupPost, String> implements IGroupPostDao
{
	public HibernateGroupPostDao()
	{
		super(GroupPost.class);
	}
}
