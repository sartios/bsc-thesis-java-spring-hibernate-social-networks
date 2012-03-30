package com.sones.facebook.keywordSearcher.logic.retriever;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.sones.facebook.dao.feed.IVideoDao;
import com.sones.facebook.downloader.model.FacebookPostDownload;
import com.sones.facebook.model.feed.Video;
import com.sones.sharedDto.facebook.keywordSearcher.feeds.ISearchableFacebookFeed;
import com.sones.sharedDto.facebook.keywordSearcher.feeds.VideoSearchDto;
import com.sones.usermanager.model.ApplicationUser;

public class VideoSearchDataManager extends	AbstractDataManager	implements	IVideoSearchDataManager
{

	/**
	 * The logger of the class.
	 */
	private	Logger	_LOGGER;
	
	/**
	 * Responsible for retrieving videos from database.
	 */
	private	IVideoDao	videoDao;
	
	/**
	 * Initlaizes the object.
	 */
	public VideoSearchDataManager()
	{
		_LOGGER	=	Logger.getLogger(VideoSearchDataManager.class);
	}
	
	@Override
	public Iterable<ISearchableFacebookFeed> getVideoForKeywordSearch(
			ApplicationUser appUser) {
		
		Set<ISearchableFacebookFeed> posts = new HashSet<ISearchableFacebookFeed>();
		Set<FacebookPostDownload>	downloadedPosts	=	(Set<FacebookPostDownload>) getManager().getStatusMessagesForKeywordSearch(appUser);
		if( downloadedPosts != null )
		{
			for( FacebookPostDownload post : downloadedPosts )
			{
				Video	video = videoDao.GetById(post.getId().getPost().getId());
				if( video != null )
				{
					VideoSearchDto videoDto = new VideoSearchDto();
					getMapper().map(video, videoDto);
					posts.add(videoDto);
				}
			}
		}
		return posts;
		
	}

	/**
	 * @param videoDao the videoDao to set
	 */
	public void setVideoDao(IVideoDao videoDao) {
		this.videoDao = videoDao;
	}

	/**
	 * @return the videoDao
	 */
	public IVideoDao getVideoDao() {
		return videoDao;
	}

	@Override
	public Iterable<ISearchableFacebookFeed> getDataToBeSearched(
			ApplicationUser appUser) {
		return	getVideoForKeywordSearch(appUser);
	}

}
