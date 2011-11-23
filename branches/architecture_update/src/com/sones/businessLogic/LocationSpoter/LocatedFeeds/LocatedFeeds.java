package com.sones.businessLogic.LocationSpoter.LocatedFeeds;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Provides methods and properties for accessing a list of {@link ILocatedFeed}
 * 
 * @author Savramis	Sartios
 *
 */
public class LocatedFeeds	implements	ILocatedFeeds
{
	/**
	 * A list of feeds
	 */
	private	List< ILocatedFeed >	_feeds;
	
	/**
	 * Initiates the list.
	 */
	public	LocatedFeeds()
	{
		_feeds	=	new	ArrayList<ILocatedFeed>();
	}

	@Override
	public void Add(ILocatedFeed locatedFeed)
	{
		if( null != locatedFeed )
		{
			_feeds.add( locatedFeed );
		}
	}

	@Override
	public Iterator<ILocatedFeed> GetIterator() 
	{
		return	_feeds.iterator();
	}

	@Override
	public void AddFeeds(ILocatedFeeds feeds)
	{
		Iterator<ILocatedFeed>	iterator	=	feeds.GetIterator();
		while(iterator.hasNext())
		{
			Add(iterator.next());
		}
	}
	
}
