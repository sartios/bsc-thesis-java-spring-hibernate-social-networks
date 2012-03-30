package com.sones.facebook.keywordSearcher.logic;

import com.sones.facebook.keywordSearcher.logic.retriever.IDataRetriever;
import com.sones.usermanager.model.ApplicationUser;

/**
 * Provides methods for the keyword searching.
 * It's the service interface. Every searcher that will be used
 * by a client, must implements this interface.
 * 
 * @author sartios.sones@gmail.com.
 *
 */
public interface IKeywordSearcherService 
{
	/**
	 * Searches for keywords into every type of facebook post that were downloaded by the user
	 * and it was not already searched.<br/>
	 * The results are saved.
	 * @param appUser - The user for who the search is running.
	 */
	public	void	searchForKeywordsIntoAllFacebookPostTypes( ApplicationUser appUser );

	/**
	 * Searches for keywords into facebook status messages that were downloaded by the user
	 * and it was not already searched.<br/>
	 * The results are saved.
	 * @param appUser - The user for who the search is running.
	 */
	public	void	searchForKeywordsIntoStatusMessages( ApplicationUser appUser );
	
	/**
	 * Searches for keywords into facebook links that were downloaded by the user
	 * and it was not already searched.<br/>
	 * The results are saved.
	 * @param appUser - The user for who the search is running.
	 */
	public	void	searchForKeywordsIntoLinks( ApplicationUser appUser );
	
	/**
	 * Searches for keywords into facebook checkins that were downloaded by the user
	 * and it was not already searched.<br/>
	 * The results are saved.
	 * @param appUser - The user for who the search is running.
	 */
	public	void	searchForKeywordsIntoCheckins( ApplicationUser appUser );
	
	/**
	 * Searches for keywords into facebook photos that were downloaded by the user
	 * and it was not already searched.<br/>
	 * The results are saved.
	 * @param appUser - The user for who the search is running.
	 */
	public	void	searchForKeywordsIntoPhotos( ApplicationUser appUser );
	
	/**
	 * Searches for keywords into facebook notes that were downloaded by the user
	 * and it was not already searched.<br/>
	 * The results are saved.
	 * @param appUser - The user for who the search is running.
	 */
	public	void	searchForKeywordsIntoNotes( ApplicationUser appUser );
	
	/**
	 * Searches for keywords into facebook videos that were downloaded by the user
	 * and it was not already searched.<br/>
	 * The results are saved.
	 * @param appUser - The user for who the search is running.
	 */
	public	void	searchForKeywordsIntoVideos( ApplicationUser appUser );
	
	/**
	 * Adds a data retriever into the internal collection of retrievers.
	 * @param dataRetriever to be added.
	 */
	public	void	addDataRetriever( IDataRetriever dataRetriever );
}
