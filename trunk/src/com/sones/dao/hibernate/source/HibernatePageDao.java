package com.sones.facebook.dao.hibernate.source;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.dao.source.IPageDao;
import com.sones.facebook.model.source.Page;

public class HibernatePageDao  extends HibernateGenericDao<Page, String> implements IPageDao
{
	public HibernatePageDao()
	{
		super(Page.class);
	}
}
