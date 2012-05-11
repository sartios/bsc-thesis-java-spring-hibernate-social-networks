package com.sones.facebook.keywordSearcher.logic.retriever;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.dao.support.DaoSupport;

import com.sones.facebook.dao.feed.IStatusMessageDao;
import com.sones.facebook.downloader.model.FacebookPostDownload;
import com.sones.facebook.model.feed.StatusMessage;
import com.sones.sharedDto.facebook.keywordSearcher.feeds.ISearchableFacebookFeed;
import com.sones.sharedDto.facebook.keywordSearcher.feeds.StatusMessageSearchDto;
import com.sones.usermanager.model.ApplicationUser;

/**
 * @see {@link IStatusMessageSearchDataManager}
 * @author sartios.sones@gmail.com.
 *
 */
public class StatusMessageSearchDataManager	extends	AbstractDataManager implements IStatusMessageSearchDataManager
{
	/**
	 * The logger of the class.
	 */
	private	Logger	_LOGGER;
	
	/**
	 * Responsible for retrieving status messages from database.
	 */
	private	IStatusMessageDao	statusMessageDao;
	
	/**
	 * Initlaizes the object.
	 */
	public StatusMessageSearchDataManager()
	{
		_LOGGER	=	Logger.getLogger(StatusMessageSearchDataManager.class);
	}

	@Override
	public Iterable<ISearchableFacebookFeed> getStatusMessagesForSearch( ApplicationUser appUser, Date date )
	{

		Set<ISearchableFacebookFeed> posts = new HashSet<ISearchableFacebookFeed>();
		Set<FacebookPostDownload>	downloadedPosts	=	(Set<FacebookPostDownload>) getManager().getStatusMessagesForKeywordSearch(appUser, date);
		if( downloadedPosts == null )
		{
			_LOGGER.error( "There are not status messages to be searched" );
		}
		else if( downloadedPosts != null )
		{
			_LOGGER.warn( "There are " + downloadedPosts.size() + " status messages to be searched" );
			for( FacebookPostDownload post : downloadedPosts )
			{
				StatusMessage	statusMessage = statusMessageDao.GetById(post.getId().getPost().getId());
				if( statusMessage != null )
				{
					StatusMessageSearchDto statusMessageDto = new StatusMessageSearchDto();
					getMapper().map(statusMessage, statusMessageDto);
					posts.add(statusMessageDto);
				}
			}
		}
		return posts;
	}

	/**
	 * @return the status message dao
	 */
	public IStatusMessageDao getStatusMessageDao() {
		return statusMessageDao;
	}

	/**
	 * @param statusMessageDao the statusMessageDao to set
	 */
	public void setStatusMessageDao(IStatusMessageDao statusMessageDao) {
		this.statusMessageDao = statusMessageDao;
	}

	@Override
	public Iterable<ISearchableFacebookFeed> getDataToBeSearched(
			ApplicationUser appUser, Date date) {
		return	getStatusMessagesForSearch( appUser, date );
	}
}
