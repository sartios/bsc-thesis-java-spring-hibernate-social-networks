package com.sones.facebook.dao.hibernate.feed;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.dao.feed.IPagePostDao;
import com.sones.facebook.model.feed.PagePost;

public class HibernatePagePostDao  extends HibernateGenericDao<PagePost, String> implements IPagePostDao
{
	public HibernatePagePostDao()
	{
		super(PagePost.class);
	}
}
