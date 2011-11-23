package com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Comments;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.sones.businessLogic.CapitalWordSpotter.CapitalWordSpotter;
import com.sones.businessLogic.CapitalWordSpotter.ICapitalWordSpotter;
import com.sones.businessLogic.Facebook.FeedManager.CommentManager.FacebookComments;
import com.sones.businessLogic.Facebook.FeedManager.CommentManager.IFacebookComment;

/**
 * Provides methods for searching an entire feed comment list for words 
 * that starting with upper case letter.
 * 
 * @author Savramis Sartios
 *
 */
public class CapitalizedFacebookComments_old	extends	FacebookComments	implements	ICapitalWordsSearchableComments
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
	public	CapitalizedFacebookComments_old()
	{
		_capitalWordSpoter	=	new	CapitalWordSpotter();
		_words	=	new	ArrayList< String >();
	}
	
	/**
	 * Finds the words that start with capital letter and exist in some or all comments.
	 * @return Words that start with capital letter into the comments.
	 */
	@Override
	public List<String> GetCapitalWords() 
	{
		Iterator<IFacebookComment>	comment	=	GetIterator();
		while( comment.hasNext() )
		{
			AddWords( GetCommentCapitalWords( comment.next() ) );
		}
		return	_words;
	}
	
	/**
	 * Finds the words that start with capital letter and that exist in comment's content.
	 * @param	comment
	 * @return Words that start with capital letter into the content.
	 */
	private	List<String>	GetCommentCapitalWords( final IFacebookComment comment) 
	{
		return	_capitalWordSpoter.GetWordsThatStartWithCapitalLetter( comment.GetContent() );
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

	@Override
	public void AddComments(Collection<ICapitalWordsSearchableComment> comments) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterator<ICapitalWordsSearchableComment> GetCommentIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void AddComment(ICapitalWordsSearchableComment comment) {
		// TODO Auto-generated method stub
		
	}	
}
