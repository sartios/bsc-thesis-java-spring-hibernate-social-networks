package com.sones.businessLogic.Searcher;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SearchableFeeds implements	ISearchableFeeds
{
	private	List< ISearchableFeed >	_feeds;
	
	public	SearchableFeeds()
	{
		_feeds	=	new	ArrayList< ISearchableFeed >();
	}

	@Override
	public void AddFeed(ISearchableFeed feed)
	{
		if( null != feed )
		{
			_feeds.add( feed );
		}
	}

	@Override
	public Iterator<ISearchableFeed> GetIterator() 
	{
		return	_feeds.iterator();
	}

	@Override
	public boolean IsEmpty() 
	{
		return	_feeds.isEmpty();
	}

	@Override
	public int GetSize() 
	{
		return	_feeds.size();
	}

}
