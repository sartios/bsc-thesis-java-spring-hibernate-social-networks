package com.sones.facebook.dao.hibernate.feed;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.dao.feed.IVideoDao;
import com.sones.facebook.model.feed.Video;

public class HibernateVideoDao extends HibernateGenericDao<Video, String> implements IVideoDao
{
	public HibernateVideoDao()
	{
		super(Video.class);
	}
}
