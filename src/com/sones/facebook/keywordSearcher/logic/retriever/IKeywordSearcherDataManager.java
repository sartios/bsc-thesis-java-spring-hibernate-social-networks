package com.sones.facebook.keywordSearcher.logic.retriever;

import com.sones.sharedDto.facebook.keywordSearcher.feeds.CheckinSearchDto;
import com.sones.sharedDto.facebook.keywordSearcher.feeds.LinkSearchDto;
import com.sones.sharedDto.facebook.keywordSearcher.feeds.NoteSearchDto;
import com.sones.sharedDto.facebook.keywordSearcher.feeds.PhotoSearchDto;
import com.sones.sharedDto.facebook.keywordSearcher.feeds.StatusMessageSearchDto;
import com.sones.sharedDto.facebook.keywordSearcher.feeds.VideoSearchDto;
import com.sones.userManager.model.ApplicationUser;

/**
 * Provides methods for gathering the facebook posts that need to be searched.
 * 
 * @author sartios.sones@gmail.com.
 *
 */
public interface IKeywordSearcherDataManager 
{
	/**
	 * Finds the status messages that were not searched for this appUser keywords
	 * and were downloaded from appUser selected sources, and returns them.
	 * @param appUser
	 * @return Status messages ready for search.
	 */
	public	Iterable<StatusMessageSearchDto>	getStatusMessagesForSearch( ApplicationUser appUser );
	
	/**
	 * Finds the videos that were not searched for this appUser keywords
	 * and were downloaded from appUser selected sources, and returns them.
	 * @param appUser
	 * @return Videos ready for search.
	 */
	public	Iterable<VideoSearchDto>	getVideoForSearch( ApplicationUser appUser );
	
	/**
	 * Finds the links that were not searched for this appUser keywords
	 * and were downloaded from appUser selected sources, and returns them.
	 * @param appUser
	 * @return Links ready for search.
	 */
	public	Iterable<LinkSearchDto>	getLinkForSearch( ApplicationUser appUser );
	
	/**
	 * Finds the photos that were not searched for this appUser keywords
	 * and were downloaded from appUser selected sources, and returns them.
	 * @param appUser
	 * @return Photos ready for search.
	 */
	public	Iterable<PhotoSearchDto>	getPhotoForSearch( ApplicationUser appUser );
	
	/**
	 * Finds the checkins that were not searched for this appUser keywords
	 * and were downloaded from appUser selected sources, and returns them.
	 * @param appUser
	 * @return Checkins ready for search.
	 */
	public	Iterable<CheckinSearchDto>	getCheckinForSearch( ApplicationUser appUser );
	
	/**
	 * Finds the notes that were not searched for this appUser keywords
	 * and were downloaded from appUser selected sources, and returns them.
	 * @param appUser
	 * @return Notes ready for search.
	 */
	public	Iterable<NoteSearchDto>	getNoteForSearch( ApplicationUser appUser );
}
