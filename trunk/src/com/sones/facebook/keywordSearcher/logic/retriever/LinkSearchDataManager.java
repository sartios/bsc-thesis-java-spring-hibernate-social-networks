package com.sones.facebook.keywordSearcher.logic.retriever;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.sones.facebook.dao.feed.ILinkDao;
import com.sones.facebook.downloader.model.FacebookPostDownload;
import com.sones.facebook.model.feed.Link;
import com.sones.sharedDto.facebook.keywordSearcher.feeds.ISearchableFacebookFeed;
import com.sones.sharedDto.facebook.keywordSearcher.feeds.LinkSearchDto;
import com.sones.userManager.model.ApplicationUser;

public class LinkSearchDataManager	extends	AbstractDataManager	implements	ILinkSearchDataManager
{
	/**
	 * The logger of the class.
	 */
	private	Logger	_LOGGER;
	
	/**
	 * Responsible for retrieving {@link Link}s from database.
	 */
	private	ILinkDao	linkDao;
	
	/**
	 * Initlaizes the object.
	 */
	public LinkSearchDataManager()
	{
		_LOGGER	=	Logger.getLogger(VideoSearchDataManager.class);
	}
	
	@Override
	public Iterable<ISearchableFacebookFeed> getLinkForSearch(ApplicationUser appUser) {
		
		Set<ISearchableFacebookFeed> posts = new HashSet<ISearchableFacebookFeed>();
		Set<FacebookPostDownload>	downloadedPosts	=	(Set<FacebookPostDownload>) getManager().getStatusMessagesForKeywordSearch(appUser);
		if( downloadedPosts != null )
		{
			for( FacebookPostDownload post : downloadedPosts )
			{
				Link	link = linkDao.GetById(post.getId().getPost().getId());
				if( link != null )
				{
					LinkSearchDto linkDto = new LinkSearchDto();
					getMapper().map(link, linkDto);
					posts.add(linkDto);
				}
			}
		}
		return posts;
		
	}

	public ILinkDao getLinkDao() 
	{
		return linkDao;
	}

	public void setLinkDao(ILinkDao linkDao) 
	{
		this.linkDao = linkDao;
	}

	@Override
	public Iterable<ISearchableFacebookFeed> getDataToBeSearched( ApplicationUser appUser )
	{
		return	getLinkForSearch( appUser );
	}

}
