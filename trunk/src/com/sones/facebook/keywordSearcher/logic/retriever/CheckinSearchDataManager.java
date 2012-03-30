package com.sones.facebook.keywordSearcher.logic.retriever;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.sones.facebook.dao.feed.ICheckinDao;
import com.sones.facebook.downloader.model.FacebookPostDownload;
import com.sones.facebook.model.feed.Checkin;
import com.sones.sharedDto.facebook.keywordSearcher.feeds.CheckinSearchDto;
import com.sones.sharedDto.facebook.keywordSearcher.feeds.ISearchableFacebookFeed;
import com.sones.usermanager.model.ApplicationUser;

public class CheckinSearchDataManager	extends	AbstractDataManager	implements	ICheckinSearchDataManager
{

	/**
	 * The logger of the class.
	 */
	private	Logger	_LOGGER;
	
	/**
	 * Responsible for retrieving {@link Checkin}s from database.
	 */
	private	ICheckinDao	checkinDao;
	
	/**
	 * Initializes the object.
	 */
	public	CheckinSearchDataManager()
	{
		_LOGGER	=	Logger.getLogger(CheckinSearchDataManager.class);
	}
	
	@Override
	public Iterable<ISearchableFacebookFeed> getCheckinForSearch(
			ApplicationUser appUser) {
		
		Set<ISearchableFacebookFeed> posts = new HashSet<ISearchableFacebookFeed>();
		Set<FacebookPostDownload>	downloadedPosts	=	(Set<FacebookPostDownload>) getManager().getStatusMessagesForKeywordSearch(appUser);
		if( downloadedPosts != null )
		{
			for( FacebookPostDownload post : downloadedPosts )
			{
				Checkin	checkin = checkinDao.GetById(post.getId().getPost().getId());
				if( checkin != null )
				{
					CheckinSearchDto checkinDto = new CheckinSearchDto();
					getMapper().map( checkin, checkinDto );
					posts.add( checkinDto );
				}
			}
		}
		return posts;
		
	}

	public ICheckinDao getCheckinDao() {
		return checkinDao;
	}

	public void setCheckinDao(ICheckinDao checkinDao) {
		this.checkinDao = checkinDao;
	}

	@Override
	public Iterable<ISearchableFacebookFeed> getDataToBeSearched(
			ApplicationUser appUser) {
		return	getCheckinForSearch(appUser);
	}	
}
