package com.sones.dao.FeeLocations.Facebook;

import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.persistance.HibernateUtil;
import com.sones.businessLogic.LocationSpoter.LocatedFeeds.ILocatedFeed;
import com.sones.businessLogic.LocationSpoter.LocatedFeeds.ILocatedFeeds;
import com.sones.businessLogic.LocationSpoter.Locations.ILocation;
import com.sones.businessLogic.Searcher.Locations.ILocationFeedPersister;
import com.sones.dao.AbstractDao;

public class FacebookFeedLocationsDao	extends	AbstractDao	implements	ILocationFeedPersister
{
	public	FacebookFeedLocationsDao()
	{
		super();
	}
	
	public	void	SaveFeedsWithLocations( ILocatedFeeds	feeds )
	{
		if( null != feeds )
		{
			Iterator<ILocatedFeed>	feedIt	=	feeds.GetIterator();
			while(feedIt.hasNext())
			{
				ILocatedFeed	feed	=	feedIt.next();
				Iterator<ILocation>	locationIt	=	feed.GetLocationsIterator();
				Query	query;
				while(locationIt.hasNext())
				{
					ILocation	location	=	locationIt.next();
					String	name	=	location.GetLocationName();
					String	id	=	feed.GetID();
					
					startOperation();
					query	=	session.createSQLQuery("INSERT  INTO    sones.feeds_contain_locations   (FACEBOOK_FEEDS_PK_FEED_ID, LOCATION)   VALUES(:feed,:location);");
					query.setParameter("feed", id);
					query.setParameter("location", name);
					try
					{
						query.executeUpdate();
						tx.commit();
						session.flush();
					}
					catch (HibernateException ex)
					{
						tx.rollback();
					}
					finally
					{
						HibernateUtil.close(session);
					}
				}
			}

		}
	}
}
