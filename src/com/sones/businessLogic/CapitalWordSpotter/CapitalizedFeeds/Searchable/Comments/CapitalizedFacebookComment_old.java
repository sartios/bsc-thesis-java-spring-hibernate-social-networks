package com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Comments;

import java.util.ArrayList;
import java.util.List;

import com.sones.businessLogic.CapitalWordSpotter.CapitalWordSpotter;
import com.sones.businessLogic.CapitalWordSpotter.ICapitalWordSpotter;
import com.sones.businessLogic.Facebook.FeedManager.CommentManager.FacebookComment;

/**
 * Provides methods for searching into facebook comments for letters 
 * that start with upper case letter.
 * 
 * @author Savramis Sartios
 *
 */
public class CapitalizedFacebookComment_old	extends	FacebookComment	implements	ICapitalWordsSearchableComment
{
	/**
	 * Finds capital words into texts
	 */
	private ICapitalWordSpotter	_capitalWordSpoter;
	
	/**
	 * A list of the words
	 */
	private	List< String >	_words;
	
	/**
	 * Initialize Capital word spotter and the collection of the words
	 */
	public	CapitalizedFacebookComment_old()
	{
		_capitalWordSpoter	=	new	CapitalWordSpotter();
		_words	=	new	ArrayList< String >();
	}
	
	/**
	 * Finds the words that start with capital letter and that exist in comment's content.
	 * @return Words that start with capital letter into the content.
	 */
	@Override
	public List<String> GetCapitalWords() 
	{
		AddWords( _capitalWordSpoter.GetWordsThatStartWithCapitalLetter( GetContent() ) );

		return	_words;
	}
	
	/**
	 * Adds a list of words into the collection of words
	 * @param words
	 */
	private	void	AddWords( final List< String > words )
	{
		if( 0 < words.size())
		{
			for( int index = 0; index < words.size(); index++)
			{
				_words.add( words.get(index) );
			}
		}
	}

}
