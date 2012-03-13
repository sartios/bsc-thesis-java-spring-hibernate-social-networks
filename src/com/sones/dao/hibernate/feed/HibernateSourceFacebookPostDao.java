package com.sones.facebook.dao.hibernate.feed;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.dao.feed.ISourceFacebookPostDao;
import com.sones.facebook.model.feed.SourceFacebookPost;
import com.sones.facebook.model.feed.SourceFacebookPostId;

public class HibernateSourceFacebookPostDao extends HibernateGenericDao<SourceFacebookPost,SourceFacebookPostId> implements ISourceFacebookPostDao
{
	public HibernateSourceFacebookPostDao()
	{
		super(SourceFacebookPost.class);
	}
}
