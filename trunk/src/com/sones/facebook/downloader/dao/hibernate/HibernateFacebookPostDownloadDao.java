package com.sones.facebook.downloader.dao.hibernate;

import org.apache.log4j.Logger;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.downloader.dao.IFacebookPostDownloadDao;
import com.sones.facebook.downloader.model.FacebookPostDownload;
import com.sones.facebook.downloader.model.FacebookPostDownloadId;

public class HibernateFacebookPostDownloadDao	extends	HibernateGenericDao<FacebookPostDownload, FacebookPostDownloadId>	implements	IFacebookPostDownloadDao
{
	private	final	Logger	_LOGGER;
	
	public	HibernateFacebookPostDownloadDao()
	{
		super(FacebookPostDownload.class);
		_LOGGER	=	Logger.getLogger( HibernateFacebookPostDownloadDao.class );
	}
}
