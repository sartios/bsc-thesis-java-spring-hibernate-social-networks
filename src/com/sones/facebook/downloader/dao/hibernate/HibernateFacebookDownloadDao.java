package com.sones.facebook.downloader.dao.hibernate;

import org.apache.log4j.Logger;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.downloader.dao.IFacebookDownloadDao;
import com.sones.facebook.downloader.model.FacebookDownload;

public class HibernateFacebookDownloadDao	extends	HibernateGenericDao<FacebookDownload, String>	implements	IFacebookDownloadDao
{
	private	final	Logger	_LOGGER;
	
	public	HibernateFacebookDownloadDao()
	{
		super(FacebookDownload.class);
		_LOGGER	=	Logger.getLogger( HibernateFacebookDownloadDao.class );
	}
}
