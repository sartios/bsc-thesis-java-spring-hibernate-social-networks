package com.sones.facebook.dao.hibernate.feed;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.dao.feed.IPhotoDao;
import com.sones.facebook.model.feed.Photo;

public class HibernatePhotoDao  extends HibernateGenericDao<Photo, String> implements IPhotoDao
{
	public HibernatePhotoDao()
	{
		super(Photo.class);
	}
}
