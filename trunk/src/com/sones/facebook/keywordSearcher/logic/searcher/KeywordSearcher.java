package com.sones.facebook.keywordSearcher.logic.searcher;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.sones.sharedDto.facebook.keywordSearcher.KeywordSearchDto;
import com.sones.sharedDto.facebook.keywordSearcher.KeywordSearchResultCreateDto;
import com.sones.sharedDto.facebook.keywordSearcher.feeds.FacebookPostSearchDto;
import com.sones.sharedDto.facebook.keywordSearcher.feeds.ISearchableFacebookFeed;

/**
 * ...
 * @see	IKeywordSearcher
 * @author sartios.sones@gmail.com.
 *
 */
public class KeywordSearcher implements IKeywordSearcher
{
	/**
	 * The class logger.
	 */
	private	final	Logger	_LOGGER;
	
	/**
	 * Initializes the object.
	 */
	public	KeywordSearcher()
	{
		_LOGGER	=	Logger.getLogger( KeywordSearcher.class );
	}
	
	private	void	checkKeywords( Iterable<KeywordSearchDto> keywords )
	{
		if( keywords == null )
		{
			_LOGGER.error( "Keywords can't be null!" );
			throw	new	IllegalArgumentException( "Keywords can't be null!" );
		}
		if( keywords.iterator().hasNext() == false )
		{
			_LOGGER.error( "Keywords are empty!" );
		}
	}
	
	private	void	checkPosts( Iterable<ISearchableFacebookFeed> posts )
	{
		if( posts == null )
		{
			_LOGGER.error( "Posts can't be null!" );
			throw	new	IllegalArgumentException( "Posts can't be null!" );
		}
		if( posts.iterator().hasNext() == false )
		{
			_LOGGER.error( "Posts are empty!" );
		}
	}

	@Override
	public Iterable<KeywordSearchResultCreateDto> getKeywordSearchResults(
			Iterable<ISearchableFacebookFeed> posts,
			Iterable<KeywordSearchDto> keywords) {
	
		checkKeywords( keywords );
		checkPosts( posts );
		Set<KeywordSearchResultCreateDto>	results	=	new	HashSet<KeywordSearchResultCreateDto>();
		for( ISearchableFacebookFeed post : posts )
		{
			for( KeywordSearchDto keyword : keywords )
			{
				if( post.contains( keyword.getValue() ) )
				{
					KeywordSearchResultCreateDto	result	=	new	KeywordSearchResultCreateDto();
					result.setKeyword( keyword );
					result.setPost( (FacebookPostSearchDto) post );
					results.add( result );
				}
			}
		}
		return	results;	
	}
}
