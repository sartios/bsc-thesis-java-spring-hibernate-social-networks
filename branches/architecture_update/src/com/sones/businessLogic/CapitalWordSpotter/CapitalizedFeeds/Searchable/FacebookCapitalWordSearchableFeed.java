package com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable;

import com.sones.businessLogic.CapitalWordSpotter.CapitalWords.CapitalWords;
import com.sones.businessLogic.CapitalWordSpotter.CapitalWords.ICapitalWords;
import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Comments.ICapitalWordsSearchableComments;
import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Contents.ICapitalWordsSearchableContent;

/**
 * Provides methods and properties for search and find capital words into
 * facebook feeds.
 * 
 * @author Savramis Sartios
 *
 */
public class FacebookCapitalWordSearchableFeed	implements	ICapitalWordsSearchableFeed
{
	/**
	 * The ID of the feed.
	 */
	private	String	_feedID;
	
	/**
	 * A collection of capital words.
	 */
	private	ICapitalWords	_words;
	
	/**
	 * Content that can search into itself for words that start with capital letters.
	 */
	private	ICapitalWordsSearchableContent	_content;
	
	/**
	 * Comments that can search into themselves for words that start with capital letters.
	 */
	private	ICapitalWordsSearchableComments	_comments;
	
	/**
	 * Initializes a collection of capital words.
	 */
	public	FacebookCapitalWordSearchableFeed()
	{
		_words	=	new	CapitalWords();
	}
	
	/**
	 * 
	 * @param _comments
	 */
	public void SetComments(ICapitalWordsSearchableComments comments) 
	{
		_comments = comments;
	}

	/**
	 * 
	 * @return
	 */
	public ICapitalWordsSearchableComments GetComments() 
	{
		return _comments;
	}

	/**
	 * Sets feed content
	 * @param content
	 */
	public void SetContent( ICapitalWordsSearchableContent content) 
	{
		_content	=	content;
	}

	/**
	 * Returns feed comments
	 * @return feed comments
	 */
	public ICapitalWordsSearchableContent GetContent() 
	{
		return _content;
	}

	/**
	 * Finds the capital words that exist into Facebook feed's content and comments.
	 * @return A collection of capital words.
	 */
	@Override
	public ICapitalWords GetCapitalWords() 
	{
		GetCapitalWordsFromContent();
		GetCapitalWordsFromComments();
		
		return	_words;
	}
	
	/**
	 * Adds the capital words that exist into feed's content into the collection of the capital words.
	 */
	private	void	GetCapitalWordsFromContent()
	{
		ICapitalWordsSearchableContent	content	=	(ICapitalWordsSearchableContent) GetContent();
		_words.AddWords( content.GetCapitalWords() );
	}
	
	/**
	 * Adds the capital words that exist into feed's comments into the collection of the capital words.
	 */
	private	void	GetCapitalWordsFromComments()
	{
		ICapitalWordsSearchableComments	comments 	=	(ICapitalWordsSearchableComments) GetComments();
		if( null != comments)
		{
			_words.AddWords( comments.GetCapitalWords() );
		}
	}

	/**
	 * @return ID of the feed.
	 */
	@Override
	public String GetFeedID() 
	{
		return	_feedID;
	}

	@Override
	public void SetFeedID(String id) 
	{
		_feedID	=	id;
	}

}
