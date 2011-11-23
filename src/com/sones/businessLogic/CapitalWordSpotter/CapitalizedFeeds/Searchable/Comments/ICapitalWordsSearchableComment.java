package com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Comments;

import java.util.List;

/**
 * Provides methods for accessing capital words of a comment, in addition with its information.
 * 
 * @author Savramis	Sartios
 *
 */
public interface ICapitalWordsSearchableComment 
{
	/**
	 * Returns a list of capital words that are contained into comment.
	 * 
	 * @return	List of capital words.
	 */
	public	List< String >	GetCapitalWords();
	
	public	void	SetFeedID( String feedID );

	public void SetMessage( String message );

	public String GetFeedID();
}
