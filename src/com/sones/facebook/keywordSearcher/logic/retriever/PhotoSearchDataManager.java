package com.sones.facebook.keywordSearcher.logic.retriever;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.sones.facebook.dao.feed.IPhotoDao;
import com.sones.facebook.downloader.model.FacebookPostDownload;
import com.sones.facebook.model.feed.Photo;
import com.sones.sharedDto.facebook.keywordSearcher.feeds.ISearchableFacebookFeed;
import com.sones.sharedDto.facebook.keywordSearcher.feeds.PhotoSearchDto;
import com.sones.usermanager.model.ApplicationUser;

/**
 * ...
 * 
 * @author sartios.sones@gmail.com.
 *
 */
public class PhotoSearchDataManager	extends	AbstractDataManager	implements	IPhotoSearchDataManager
{
	/**
	 * Responsible for retrieving {@link Photo}s from database.
	 */
	private	IPhotoDao	photoDao;
	
	/**
	 * Initializes the object.
	 */
	public	PhotoSearchDataManager()
	{
		
	}
	
	@Override
	public Iterable<ISearchableFacebookFeed> getPhotoForKeywordSearch(ApplicationUser appUser, Date date) 
	{
		Set<ISearchableFacebookFeed> posts = new HashSet<ISearchableFacebookFeed>();
		Set<FacebookPostDownload>	downloadedPosts	=	(Set<FacebookPostDownload>) getManager().getStatusMessagesForKeywordSearch(appUser, date);
		if( downloadedPosts != null )
		{
			for( FacebookPostDownload post : downloadedPosts )
			{
				Photo	photo = photoDao.GetById(post.getId().getPost().getId());
				if( photo != null )
				{
					PhotoSearchDto photoDto = new PhotoSearchDto();
					getMapper().map(photo, photoDto);
					posts.add(photoDto);
				}
			}
		}
		return posts;
	}

	/**
	 * @param photoDao the photoDao to set
	 */
	public void setPhotoDao(IPhotoDao photoDao) {
		this.photoDao = photoDao;
	}

	/**
	 * @return the photoDao
	 */
	public IPhotoDao getPhotoDao() {
		return photoDao;
	}

	@Override
	public Iterable<ISearchableFacebookFeed> getDataToBeSearched(
			ApplicationUser appUser, Date date) {
		return	getPhotoForKeywordSearch(appUser, date);
	}
}
