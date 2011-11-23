package com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FacebookCapitalWordFeeds	implements	ICapitalWordsSearchableFeeds
{

	private	List<ICapitalWordsSearchableFeed>	_feeds;
	
	public	FacebookCapitalWordFeeds()
	{
		_feeds	=	new	ArrayList<ICapitalWordsSearchableFeed>();
	}
	
	public	void	AddFeeds(List<ICapitalWordsSearchableFeed>	feeds)
	{
		_feeds.addAll(feeds);
	}
	
	@Override
	public Iterator<ICapitalWordsSearchableFeed> GetIterator() 
	{
		return	_feeds.iterator();
	}

}
