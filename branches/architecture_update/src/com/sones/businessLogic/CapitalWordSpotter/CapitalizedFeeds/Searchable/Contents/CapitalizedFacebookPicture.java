package com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Contents;

import java.util.ArrayList;
import java.util.List;

import com.sones.businessLogic.CapitalWordSpotter.CapitalWordSpotter;
import com.sones.businessLogic.CapitalWordSpotter.ICapitalWordSpotter;
import com.sones.businessLogic.Facebook.FeedManager.PictureContent;

/**
 * Provides methods for find words that start with capital letter inside Facebook picture content
 * 
 * @author Savramis Sartio
 *
 */
public class CapitalizedFacebookPicture extends	PictureContent	implements	ICapitalWordsSearchableContent
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
	public	CapitalizedFacebookPicture()
	{
		_capitalWordSpoter	=	new	CapitalWordSpotter();
		_words	=	new	ArrayList< String >();
	}
	
	/**
	 * Finds the words that start with capital letter and that exist in its content.
	 * @return Words that start with capital letter and exist into the content.
	 */
	@Override
	public List<String> GetCapitalWords() 
	{
		AddWords( _capitalWordSpoter.GetWordsThatStartWithCapitalLetter( GetMessage() ) );
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
