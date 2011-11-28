package com.sones.businessLogic.Searcher.Results;

import java.util.Collection;
import java.util.List;

public interface IResultViewModel
{
	/**
	 * Returns the content of the result. Not the full content
	 * but an attributive string.
	 * @return Attributive content.
	 */
	public	String	GetContent();
	
	/**
	 * The list of keywords.
	 *
	 * @return Keywords.
	 */
	public	List<String>	GetKeywords();
	
	/**
	 * Returns the creation date of the feed.
	 * 
	 * @return Feed's creation date.
	 */
	public	String	GetCreationDate();
	
	/**
	 * Returns the creator of the feed.
	 * 
	 * @return Feed's creator.
	 */
	public	String	GetCreator();
	
	/**
	 * Returns the type of the feed.
	 * 
	 * @return Feed's type.
	 */
	public	String	GetType();
	
	/**
	 * Returns the IDs of the feed.
	 * 
	 * @return Feed's ID.
	 */
	public	String	GetFeedID();
	
	/**
	 * Add keyword
	 */
	public	void	AddKeyword( final String keyword);
	
	/**
	 * Add keywords
	 */
	public	void	AddKeyword( final Collection<String> keywords);
}
