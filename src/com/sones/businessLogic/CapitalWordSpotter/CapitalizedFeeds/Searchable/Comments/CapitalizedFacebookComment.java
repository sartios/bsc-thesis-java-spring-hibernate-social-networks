package com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Comments;

import java.util.ArrayList;
import java.util.List;

import com.sones.businessLogic.CapitalWordSpotter.CapitalWordSpotter;
import com.sones.businessLogic.CapitalWordSpotter.ICapitalWordSpotter;

public class CapitalizedFacebookComment	implements	ICapitalWordsSearchableComment
{
	
	private	String	_feedID;
	private	String	_message;
	
	/**
	 * Finds capital words into texts
	 */
	private ICapitalWordSpotter	_capitalWordSpoter;
	
	/**
	 * A list of the words
	 */
	private	List< String >	_words;
	
	public	CapitalizedFacebookComment()
	{		
		_capitalWordSpoter	=	new	CapitalWordSpotter();
		_words	=	new	ArrayList< String >();
	}

	public void SetFeedID(String feedID) 
	{
		_feedID = feedID;
	}
	
	public String GetMessage()
	{
		return _message;
	}

	public void SetMessage(String message)
	{
		_message = message;
	}

	public String GetFeedID()
	{
		return _feedID;
	}
	
	/**
	 * Finds the words that start with capital letter and that exist in comment's content.
	 * @return Words that start with capital letter into the content.
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
