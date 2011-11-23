package com.sones.businessLogic.LocationSpoter;

import java.util.Iterator;

import com.sones.businessLogic.CapitalWordSpotter.CapitalWords.ICapitalWord;
import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.ICapitalWordsSearchableFeed;
import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.ICapitalWordsSearchableFeeds;
import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Comments.ICapitalWordsSearchableComment;
import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Comments.ICapitalWordsSearchableComments;
import com.sones.businessLogic.LocationSpoter.LocatedFeeds.ILocatedFeed;
import com.sones.businessLogic.LocationSpoter.LocatedFeeds.ILocatedFeeds;
import com.sones.businessLogic.LocationSpoter.LocatedFeeds.LocatedFeed;
import com.sones.businessLogic.LocationSpoter.LocatedFeeds.LocatedFeeds;
import com.sones.businessLogic.LocationSpoter.Locations.ILocation;
import com.sones.businessLogic.LocationSpoter.Locations.ILocations;


/**
 * Provides methods and properties for specifying the locations that exist
 * into a feed. It uses GeoNames.
 * 
 * @see {@link http://www.geonames.org/}
 * 
 * @author Savramis	Sartios
 *
 */
public class FeedLocationSpotter	implements	IFeedLocationSpotter
{
	/**
	 * A client for finding real locations.
	 */
	private	ILocationHandler	_locationHandler;
	
	private	IFeedProvider	_feedProvider;
	
	/**
	 * Initiates location handler to access GeoNames.
	 */
	public	FeedLocationSpotter()
	{
		_locationHandler	=	new	LocationHandler();
		_feedProvider	=	new	FacebookFeedProvider();
	}
	
	public	ILocatedFeed	GetFeedWithLocations( final ICapitalWordsSearchableFeed feed )
	{
		Iterator< ICapitalWord >	iterator	=	feed.GetCapitalWords().GetIterator();
		ILocatedFeed	locatedFeed	=	new	LocatedFeed();
		locatedFeed.SetFeedID( feed.GetFeedID() );
		while(	iterator.hasNext() )
		{
			ICapitalWord	word	=	iterator.next();
			ILocation	location	=	_locationHandler.GetLocation( word.GetValue() );
			locatedFeed.Add( location );
		}
		return	locatedFeed;
	}

	@Override
	public ILocatedFeeds GetFeedsWithLocations( ICapitalWordsSearchableFeeds feeds )
	{
		ILocatedFeeds	locatedFeeds	=	new	LocatedFeeds();
		Iterator< ICapitalWordsSearchableFeed >	iterator	=	feeds.GetIterator();
		while( iterator.hasNext() )
		{
			ICapitalWordsSearchableFeed	feed	=	iterator.next();
			locatedFeeds.Add( GetFeedWithLocations(feed) );
		}
		
		return	locatedFeeds;
	}
	
	@Override
	public	ILocatedFeeds	GetFeedsWithLocations(String startDate, String endDate)
	{
		ICapitalWordsSearchableFeeds	feeds	=	_feedProvider.GetFeedsBetweenDates(startDate, endDate);
		ICapitalWordsSearchableComments	comments	=	_feedProvider.GetCommentsBetweenDates(startDate, endDate);
		ILocatedFeeds	locatedFeeds	=	new	LocatedFeeds();
		locatedFeeds.AddFeeds(GetFeedsWithLocations(feeds));
		locatedFeeds.AddFeeds(GetFeedWithLocations(comments));
		return	GetFeedsWithLocations(feeds);
	}

	@Override
	public ILocatedFeeds GetFeedWithLocations( ICapitalWordsSearchableComments comments ) 
	{
		ILocatedFeeds	locatedFeeds	=	new	LocatedFeeds();
		Iterator< ICapitalWordsSearchableComment >	iterator	=	comments.GetCommentIterator();
		while( iterator.hasNext() )
		{
			ICapitalWordsSearchableComment	comment	=	iterator.next();
			locatedFeeds.Add( GetFeedWithCommentLocations(comment) );
		}
		
		return	locatedFeeds;
	}
	
	public	ILocatedFeed	GetFeedWithCommentLocations( final ICapitalWordsSearchableComment comment )
	{
		Iterator< String >	iterator	=	comment.GetCapitalWords().iterator();
		ILocatedFeed	locatedFeed	=	new	LocatedFeed();
		locatedFeed.SetFeedID( comment.GetFeedID() );
		while(	iterator.hasNext() )
		{
			String	word	=	iterator.next();
			ILocation	location	=	_locationHandler.GetLocation( word );
			locatedFeed.Add( location );
		}
		return	locatedFeed;
	}
}
