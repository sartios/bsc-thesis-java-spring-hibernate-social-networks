package com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Contents;

import java.util.ArrayList;
import java.util.List;

import com.sones.businessLogic.CapitalWordSpotter.CapitalWordSpotter;
import com.sones.businessLogic.CapitalWordSpotter.ICapitalWordSpotter;
import com.sones.businessLogic.Facebook.FeedManager.LinkContent;

public class CapitalizedFacebookLink extends LinkContent	implements	ICapitalWordsSearchableContent
{

	/**
	 * Finds capital words into texts
	 */
	private ICapitalWordSpotter	_capitalWordSpoter;
	
	/**
	 * A list of the words
	 */
	private	List< String >	_words;
	
	public CapitalizedFacebookLink()
	{
		_capitalWordSpoter	=	new	CapitalWordSpotter();
		_words	=	new	ArrayList< String >();
	}
	
	@Override
	public List<String> GetCapitalWords() 
	{	
		AddWords( _capitalWordSpoter.GetWordsThatStartWithCapitalLetter( GetCaption() ) );
		AddWords( _capitalWordSpoter.GetWordsThatStartWithCapitalLetter( GetDescription() ) );
		AddWords( _capitalWordSpoter.GetWordsThatStartWithCapitalLetter( GetMessage() ) );
		AddWords( _capitalWordSpoter.GetWordsThatStartWithCapitalLetter( GetMessage() ) );
		AddWords( _capitalWordSpoter.GetWordsThatStartWithCapitalLetter( GetName() ) );
		return	_words;
	}
	
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
