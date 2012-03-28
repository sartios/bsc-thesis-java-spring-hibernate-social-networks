package com.sones.facebook.keywordSearcher.logic.retriever;

import com.sones.facebook.downloader.model.FacebookPostDownload;
import com.sones.userManager.model.ApplicationUser;

/**
 * Provides methods for retrieving facebook feeds for search.
 * 
 * @author sartios.sones@gmail.com.
 *
 */
public interface IFacebookPostDataManager 
{
	/**
	 * Returns the {@link FacebookPostDownload}s that were not searched for keywords.
	 * @param appUser
	 * @return {@link FacebookPostDownload}s that were not searched for keywords.
	 */
	public	Iterable<FacebookPostDownload>	getStatusMessagesForKeywordSearch( ApplicationUser appUser );
}
