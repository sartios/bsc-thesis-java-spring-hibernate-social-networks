package com.sones.facebook.keywordSearcher.logic.retriever;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.sones.facebook.downloader.dao.IFacebookPostDownloadDao;
import com.sones.facebook.downloader.model.FacebookPostDownload;
import com.sones.facebook.keywordSearcher.dao.IKeywordSearchDao;
import com.sones.sharedDto.facebook.keywordSearcher.feeds.StatusMessageSearchDto;
import com.sones.usermanager.model.ApplicationUser;

public class FacebookPostDataManager implements	IFacebookPostDataManager
{
	private	final	Logger	_LOGGER;
	
	/**
	 * Responsible for retrieving keyword searches from database.
	 */
	private	IKeywordSearchDao	keywordSearchDao;

	/**
	 * Responsible for retrieving the facebook posts per download.
	 */
	private	IFacebookPostDownloadDao	facebookPostDownloadDao;
	
	/**
	 * Initializes the object.s
	 */
	public	FacebookPostDataManager()
	{
		_LOGGER	=	Logger.getLogger( FacebookPostDataManager.class );
	}

	@Override
	public Iterable<FacebookPostDownload> getStatusMessagesForKeywordSearch(ApplicationUser appUser, Date date) 
	{
		_LOGGER.warn( "Retrieving posts that were downloaded after: " + date.toString() );
		Set<StatusMessageSearchDto> posts = new HashSet<StatusMessageSearchDto>();
		Set<FacebookPostDownload>	downloadedPosts	=	(Set<FacebookPostDownload>) facebookPostDownloadDao.getFacebookPostAfterDateByAppUser(date, appUser);
		return downloadedPosts;
	}
	
	public IKeywordSearchDao getKeywordSearchDao() {
		return keywordSearchDao;
	}

	public void setKeywordSearchDao(IKeywordSearchDao keywordSearchDao) {
		this.keywordSearchDao = keywordSearchDao;
	}

	public IFacebookPostDownloadDao getFacebookPostDownloadDao() {
		return facebookPostDownloadDao;
	}

	public void setFacebookPostDownloadDao(
			IFacebookPostDownloadDao facebookPostDownloadDao) {
		this.facebookPostDownloadDao = facebookPostDownloadDao;
	}
}
